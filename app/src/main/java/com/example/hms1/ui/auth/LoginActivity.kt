package com.example.hms1.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.models.User
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityLoginBinding
import com.example.hms1.ui.admin.AdminDashboardActivity
import com.example.hms1.ui.student.StudentDashboardActivity
import com.example.hms1.ui.worker.WorkerDashboardActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLoginButton()
        setupSignupButton()
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val result = authRepository.signIn(email, password)
                    result.onSuccess { user ->
                        when (user) {
                            is User.Admin -> {
                                Toast.makeText(this@LoginActivity, "Welcome, Admin!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
                                finish()
                            }
                            is User.Student -> {
                                Toast.makeText(this@LoginActivity, "Welcome, Student!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, StudentDashboardActivity::class.java))
                                finish()
                            }
                            is User.Worker -> {
                                Toast.makeText(this@LoginActivity, "Welcome, Worker!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, WorkerDashboardActivity::class.java))
                                finish()
                            }
                        }
                    }.onFailure { error ->
                        Toast.makeText(this@LoginActivity, "Login failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupSignupButton() {
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, AdminSignupActivity::class.java))
        }
    }
} 