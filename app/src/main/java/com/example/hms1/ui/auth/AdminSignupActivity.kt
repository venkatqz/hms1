package com.example.hms1.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.databinding.ActivityAdminSignupBinding
import com.example.hms1.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AdminSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminSignupBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSignupButton()
    }

    private fun setupSignupButton() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val block = binding.etBlock.text.toString()
            val secretKey = binding.etSecretKey.text.toString()

            if (name.isBlank() || email.isBlank() || password.isBlank() || 
                block.isBlank() || secretKey.isBlank()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = authRepository.createAdmin(name, email, password, block, secretKey)
                result.fold(
                    onSuccess = {
                        Toast.makeText(this@AdminSignupActivity, 
                            "Admin account created successfully", 
                            Toast.LENGTH_SHORT).show()
                        finish()
                    },
                    onFailure = { exception ->
                        showError(exception.message ?: "Failed to create admin account")
                    }
                )
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 