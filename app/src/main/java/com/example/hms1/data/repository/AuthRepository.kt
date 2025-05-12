package com.example.hms1.data.repository

import android.util.Log
import com.example.hms1.data.models.User
import com.example.hms1.data.models.UserRole
import com.example.hms1.data.models.UserType
import com.example.hms1.data.models.WorkerType
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private val studentsCollection = db.collection("students")

    suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<User.Admin> {
        try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception("Authentication failed"))

            // Check if user exists in any collection
            val adminDoc = db.collection("admins").document(userId).get().await()

            return if (adminDoc.exists()) {
                Result.success(adminDoc.toObject(User.Admin::class.java)!!)
            } else {
                Result.failure(Exception("User not registered as admin"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Google sign in failed: ${e.message}"))
        }
    }

    suspend fun signInWithEmailPassword(email: String, password: String): Result<User.Admin> {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: return Result.failure(Exception("Invalid credentials"))

            val adminDoc = db.collection("admins").document(userId).get().await()

            return if (adminDoc.exists()) {
                Result.success(adminDoc.toObject(User.Admin::class.java)!!)
            } else {
                Result.failure(Exception("User not registered as admin"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Login failed: ${e.message}"))
        }
    }

    private suspend fun verifyAdminCredentials(secretKey: String): Boolean {
        return try {
            val configDoc = db.collection("config").document("admin_config").get().await()
            val storedSecretKey = configDoc.getString("secret_key")
            storedSecretKey == secretKey
        } catch (e: Exception) {
            false
        }
    }

    suspend fun createAdmin(
        name: String,
        email: String,
        password: String,
        block: String,
        secretKey: String
    ): Result<User.Admin> {
        if (!verifyAdminCredentials(secretKey)) {
            return Result.failure(Exception("Invalid secret key"))
        }

        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception("Authentication failed"))

            val admin = User.Admin(
                id = userId,
                name = name,
                email = email,
                roomNumber = "",
                block = block
            )

            db.collection("admins").document(userId).set(admin).await()
            return Result.success(admin)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to create admin: ${e.message}"))
        }
    }

    suspend fun setupAdminConfig(secretKey: String): Result<Boolean> {
        return try {
            db.collection("config")
                .document("admin_config")
                .set(mapOf("secret_key" to secretKey))
                .await()

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to set up admin configuration: ${e.message}"))
        }
    }

    suspend fun updateAdminConfig(
        newSecretKey: String?,
        currentAdminId: String
    ): Result<Boolean> {
        return try {
            // Verify that the user is an admin
            val adminDoc = db.collection("admins").document(currentAdminId).get().await()
            if (!adminDoc.exists()) {
                return Result.failure(Exception("Only admins can update the configuration"))
            }

            if (newSecretKey != null) {
                db.collection("config")
                    .document("admin_config")
                    .update("secret_key", newSecretKey)
                    .await()
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to update admin configuration: ${e.message}"))
        }
    }

    suspend fun createUser(
        name: String,
        email: String,
        password: String?,
        userType: UserType,
        additionalData: Map<String, String>
    ): Result<User> {
        try {
            // Verify secret key for all user types
            val secretKey = additionalData["secretKey"] ?: return Result.failure(Exception("Secret key required"))
            if (!verifySecretKey(secretKey)) {
                return Result.failure(Exception("Invalid secret key"))
            }

            // Create Firebase auth user
            val authResult = if (password != null) {
                auth.createUserWithEmailAndPassword(email, password).await()
            } else {
                // For Google Sign-In users who don't have a password
                auth.currentUser ?: return Result.failure(Exception("No authenticated user"))
                null
            }

            val userId = authResult?.user?.uid ?: auth.currentUser?.uid 
                ?: return Result.failure(Exception("Authentication failed"))

            // Create user document based on type
            val user = when (userType) {
                UserType.STUDENT -> User.Student(
                    id = userId,
                    name = name,
                    email = email,
                    roomNumber = additionalData["roomNumber"] ?: "",
                    block = additionalData["block"] ?: "",
                    phoneNumber = additionalData["phoneNumber"] ?: "",
                    dateOfBirth = additionalData["dateOfBirth"]?.let { Date(it.toLong()) } ?: Date()
                )
                UserType.ADMIN -> User.Admin(
                    id = userId,
                    name = name,
                    email = email,
                    roomNumber = additionalData["roomNumber"] ?: "",
                    block = additionalData["block"] ?: ""
                )
                UserType.WORKER -> User.Worker(
                    id = userId,
                    name = name,
                    email = email,
                    workerType = WorkerType.valueOf(additionalData["workerType"] ?: "CLEANING"),
                    roomNumber = additionalData["roomNumber"] ?: "",
                    block = additionalData["block"] ?: "",
                    phoneNumber = additionalData["phoneNumber"] ?: "",
                    dateOfBirth = additionalData["dateOfBirth"]?.let { Date(it.toLong()) } ?: Date()
                )
            }

            // Save to appropriate collection
            val collection = when (userType) {
                UserType.STUDENT -> "students"
                UserType.ADMIN -> "admins"
                UserType.WORKER -> "workers"
            }
            
            db.collection(collection).document(userId).set(user).await()
            return Result.success(user)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to create user: ${e.message}"))
        }
    }

    private suspend fun verifySecretKey(secretKey: String): Boolean {
        return try {
            val configDoc = db.collection("config").document("admin_config").get().await()
            val storedKey = configDoc.getString("secret_key")
            storedKey == secretKey
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateSecretKey(newKey: String, adminId: String): Result<Boolean> {
        return try {
            // Verify that the user is an admin
            val adminDoc = db.collection("admins").document(adminId).get().await()
            if (!adminDoc.exists()) {
                return Result.failure(Exception("Only admins can update the secret key"))
            }

            db.collection("config")
                .document("admin_config")
                .set(mapOf("secret_key" to newKey))
                .await()

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to update secret key: ${e.message}"))
        }
    }

    suspend fun createStudent(
        name: String,
        email: String,
        password: String,
        roomNumber: String,
        block: String,
        phoneNumber: String,
        dateOfBirth: Date,
        adminId: String
    ): Result<User.Student> {
        return try {
            // Verify that the user is an admin
            val adminDoc = db.collection("admins").document(adminId).get().await()
            if (!adminDoc.exists()) {
                return Result.failure(Exception("Only admins can create students"))
            }

            // Create Firebase auth user
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception("Authentication failed"))

            // Create student document
            val student = User.Student(
                id = userId,
                name = name,
                email = email,
                roomNumber = roomNumber,
                block = block,
                phoneNumber = phoneNumber,
                dateOfBirth = dateOfBirth
            )

            // Save to students collection
            db.collection("students").document(userId).set(student).await()
            Result.success(student)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to create student: ${e.message}"))
        }
    }

    suspend fun registerStudent(
        email: String,
        password: String,
        name: String,
        roomNumber: String,
        block: String,
        phoneNumber: String,
        dateOfBirth: Date
    ): Result<User.Student> {
        return try {
            // Create user in Firebase Auth
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Failed to create user")

            // Create student document
            val student = User.Student(
                id = userId,
                name = name,
                email = email,
                roomNumber = roomNumber,
                block = block,
                phoneNumber = phoneNumber,
                dateOfBirth = dateOfBirth
            )
            studentsCollection.document(userId).set(student).await()

            Result.success(student)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerAdmin(
        email: String,
        password: String,
        name: String,
        roomNumber: String,
        block: String
    ): Result<User.Admin> {
        return try {
            // Create user in Firebase Auth
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Failed to create user")

            // Create admin document
            val admin = User.Admin(
                id = userId,
                name = name,
                email = email,
                roomNumber = roomNumber,
                block = block
            )
            usersCollection.document(userId).set(admin).await()

            Result.success(admin)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Failed to login")

            val userDoc = usersCollection.document(userId).get().await()
            val user = userDoc.toObject(User::class.java) ?: throw Exception("User not found")

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getStudentProfile(userId: String): Result<User.Student> {
        return try {
            val studentDoc = studentsCollection.document(userId).get().await()
            val student = studentDoc.toObject(User.Student::class.java) ?: throw Exception("Student not found")
            Result.success(student)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        auth.signOut()
    }

    suspend fun signIn(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                val userData = getUserData(user.uid)
                Result.success(userData)
            } else {
                Result.failure(Exception("Authentication failed"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign in failed", e)
            Result.failure(e)
        }
    }

    suspend fun signUpStudent(
        email: String,
        password: String,
        name: String,
        roomNumber: String,
        block: String,
        phoneNumber: String,
        dateOfBirth: Date
    ): Result<User.Student> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                val student = User.Student(
                    id = user.uid,
                    name = name,
                    email = email,
                    roomNumber = roomNumber,
                    block = block,
                    phoneNumber = phoneNumber,
                    dateOfBirth = dateOfBirth
                )
                usersCollection.document(user.uid).set(student).await()
                Result.success(student)
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign up failed", e)
            Result.failure(e)
        }
    }

    suspend fun signUpWorker(
        email: String,
        password: String,
        name: String,
        workerType: WorkerType,
        roomNumber: String,
        block: String,
        phoneNumber: String,
        dateOfBirth: Date
    ): Result<User.Worker> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                val worker = User.Worker(
                    id = user.uid,
                    name = name,
                    email = email,
                    workerType = workerType,
                    roomNumber = roomNumber,
                    block = block,
                    phoneNumber = phoneNumber,
                    dateOfBirth = dateOfBirth
                )
                usersCollection.document(user.uid).set(worker).await()
                Result.success(worker)
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign up failed", e)
            Result.failure(e)
        }
    }

    suspend fun signUpAdmin(
        email: String,
        password: String,
        name: String,
        roomNumber: String,
        block: String
    ): Result<User.Admin> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                val admin = User.Admin(
                    id = user.uid,
                    name = name,
                    email = email,
                    roomNumber = roomNumber,
                    block = block
                )
                usersCollection.document(user.uid).set(admin).await()
                Result.success(admin)
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign up failed", e)
            Result.failure(e)
        }
    }

    suspend fun getUserData(userId: String): User {
        val doc = usersCollection.document(userId).get().await()
        return when (doc.getString("userType")) {
            UserType.STUDENT.name -> doc.toObject(User.Student::class.java) ?: throw Exception("Invalid student data")
            UserType.ADMIN.name -> doc.toObject(User.Admin::class.java) ?: throw Exception("Invalid admin data")
            UserType.WORKER.name -> doc.toObject(User.Worker::class.java) ?: throw Exception("Invalid worker data")
            else -> throw Exception("Invalid user type")
        }
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
} 