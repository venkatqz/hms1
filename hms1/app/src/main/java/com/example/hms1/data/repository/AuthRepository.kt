package com.example.hms1.data.repository

import com.example.hms1.data.models.User
import com.example.hms1.data.models.UserType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AuthRepository {
    private val dateFormat = SimpleDateFormat("ddMMMyy", Locale.getDefault())
    
    suspend fun loginStudent(registrationNumber: String, dateOfBirth: String): Result<User> {
        // TODO: Implement Firebase authentication
        // For now, return mock data
        return Result.success(
            User(
                id = "1",
                name = "Test Student",
                userType = UserType.STUDENT,
                password = dateOfBirth,
                registrationNumber = registrationNumber,
                dateOfBirth = dateOfBirth
            )
        )
    }

    suspend fun loginAdmin(email: String, password: String): Result<User> {
        // TODO: Implement Firebase authentication
        return Result.success(
            User(
                id = "2",
                name = "Test Admin",
                userType = UserType.ADMIN,
                password = password,
                block = "A"
            )
        )
    }

    suspend fun loginWorker(workerId: String, password: String): Result<User> {
        // TODO: Implement Firebase authentication
        return Result.success(
            User(
                id = "3",
                name = "Test Worker",
                userType = UserType.WORKER,
                password = password,
                workerId = workerId
            )
        )
    }

    suspend fun createStudent(
        name: String,
        registrationNumber: String,
        roomNumber: String,
        dateOfBirth: String
    ): Result<User> {
        // Format date of birth to DDMMMYY format
        val formattedDate = try {
            val date = dateFormat.parse(dateOfBirth)
            dateFormat.format(date!!)
        } catch (e: Exception) {
            return Result.failure(Exception("Invalid date format. Use DDMMMYY"))
        }

        val student = User(
            id = System.currentTimeMillis().toString(),
            name = name,
            userType = UserType.STUDENT,
            password = formattedDate, // Initial password is DOB
            registrationNumber = registrationNumber,
            roomNumber = roomNumber,
            dateOfBirth = formattedDate
        )
        
        // TODO: Save to Firebase
        return Result.success(student)
    }

    suspend fun createAdmin(
        name: String,
        email: String,
        password: String,
        block: String,
        secretKey: String
    ): Result<User> {
        // Verify secret key
        if (secretKey != "ADMIN_SECRET_KEY") { // TODO: Use secure secret key
            return Result.failure(Exception("Invalid secret key"))
        }

        val admin = User(
            id = System.currentTimeMillis().toString(),
            name = name,
            userType = UserType.ADMIN,
            password = password,
            block = block
        )
        
        // TODO: Save to Firebase
        return Result.success(admin)
    }

    suspend fun createWorker(
        name: String,
        workerId: String,
        password: String,
        workerType: String
    ): Result<User> {
        val worker = User(
            id = System.currentTimeMillis().toString(),
            name = name,
            userType = UserType.WORKER,
            password = password,
            workerId = workerId,
            workerType = WorkerType.valueOf(workerType)
        )
        
        // TODO: Save to Firebase
        return Result.success(worker)
    }

    suspend fun changePassword(userId: String, oldPassword: String, newPassword: String): Result<Unit> {
        // TODO: Implement password change logic
        return Result.success(Unit)
    }
} 