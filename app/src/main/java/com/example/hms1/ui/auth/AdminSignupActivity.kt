package com.example.hms1.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityAdminSignupBinding
import com.example.hms1.ui.admin.AdminDashboardActivity
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
                            Toast.makeText(this@AdminSignupActivity,
                                "Admin registration successful", Toast.LENGTH_SHORT).show()
                            startActivity(android.content.Intent(this@AdminSignupActivity, AdminDashboardActivity::class.java))
                            finish()
                        },
                        onFailure = { error ->
                            Toast.makeText(this@AdminSignupActivity,
                                "Registration failed: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                    )
                } catch (e: Exception) {
                    Toast.makeText(this@AdminSignupActivity,
                        "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
} 