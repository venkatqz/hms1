package com.example.hms1.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityAdminSignupBinding
import com.example.hms1.ui.auth.LoginActivity
import kotlinx.coroutines.launch

class AdminSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminSignupBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val block = binding.etBlock.text.toString()
            val secretKey = binding.etSecretKey.text.toString()

            if (validateInputs(name, email, password, block, secretKey)) {
                performSignup(name, email, password, block, secretKey)
            }
        }
    }

    private fun validateInputs(
        name: String,
        email: String,
        password: String,
        block: String,
        secretKey: String
    ): Boolean {
        if (name.isBlank()) {
            binding.etName.error = "Name is required"
            return false
        }
        if (email.isBlank()) {
            binding.etEmail.error = "Email is required"
            return false
        }
        if (password.isBlank()) {
            binding.etPassword.error = "Password is required"
            return false
        }
        if (block.isBlank()) {
            binding.etBlock.error = "Block is required"
            return false
        }
        if (secretKey.isBlank()) {
            binding.etSecretKey.error = "Secret key is required"
            return false
        }
        return true
    }

    private fun performSignup(
        name: String,
        email: String,
        password: String,
        block: String,
        secretKey: String
    ) {
        lifecycleScope.launch {
            try {
                binding.btnSignup.isEnabled = false
                binding.btnSignup.text = "Registering..."
                
                val result = authRepository.createAdmin(
                    name = name,
                    email = email,
                    password = password,
                    block = block,
                    secretKey = secretKey
                )
                
                result.onSuccess { admin ->
                    Toast.makeText(
                        this@AdminSignupActivity,
                        "Admin registration successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    startActivity(Intent(this@AdminSignupActivity, LoginActivity::class.java))
                    finish()
                }.onFailure { error ->
                    val errorMessage = when {
                        error.message?.contains("permission-denied") == true -> 
                            "Permission denied. Please check your secret key."
                        error.message?.contains("email-already-in-use") == true ->
                            "This email is already registered."
                        error.message?.contains("weak-password") == true ->
                            "Password is too weak. Please use a stronger password."
                        else -> "Registration failed: ${error.message}"
                    }
                    
                    Toast.makeText(
                        this@AdminSignupActivity,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@AdminSignupActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                binding.btnSignup.isEnabled = true
                binding.btnSignup.text = "Register as Admin"
            }
        }
    }
} 