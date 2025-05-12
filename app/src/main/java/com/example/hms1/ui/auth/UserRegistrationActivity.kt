package com.example.hms1.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.R
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityUserRegistrationBinding
import com.example.hms1.ui.admin.AdminDashboardActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

class UserRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserRegistrationBinding
    private val authRepository = AuthRepository()
    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            handleGoogleSignIn(account)
        } catch (e: ApiException) {
            Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGoogleSignIn()
        setupRegistrationButtons()
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("339012919717-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnGoogleSignIn.setOnClickListener {
            googleSignInLauncher.launch(googleSignInClient.signInIntent)
        }
    }

    private fun setupRegistrationButtons() {
        binding.btnEmailSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val block = binding.etBlock.text.toString()
            val secretKey = binding.etSecretKey.text.toString()

            if (name.isBlank() || email.isBlank() || password.isBlank() || 
                block.isBlank() || secretKey.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val result = authRepository.createAdmin(
                        name = name,
                        email = email,
                        password = password,
                        block = block,
                        secretKey = secretKey
                    )

                    result.fold(
                        onSuccess = {
                            Toast.makeText(this@UserRegistrationActivity, 
                                "Admin registration successful", Toast.LENGTH_SHORT).show()
                            navigateToDashboard()
                        },
                        onFailure = { error ->
                            Toast.makeText(this@UserRegistrationActivity,
                                "Registration failed: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                    )
                } catch (e: Exception) {
                    Toast.makeText(this@UserRegistrationActivity,
                        "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun handleGoogleSignIn(account: com.google.android.gms.auth.api.signin.GoogleSignInAccount) {
        lifecycleScope.launch {
            try {
                val result = authRepository.signInWithGoogle(account)
                result.fold(
                    onSuccess = {
                        Toast.makeText(this@UserRegistrationActivity,
                            "Google sign in successful", Toast.LENGTH_SHORT).show()
                        navigateToDashboard()
                    },
                    onFailure = { error ->
                        Toast.makeText(this@UserRegistrationActivity,
                            "Google sign in failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                )
            } catch (e: Exception) {
                Toast.makeText(this@UserRegistrationActivity,
                    "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, AdminDashboardActivity::class.java))
        finish()
    }
} 