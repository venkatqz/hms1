package com.example.hms1.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.R
import com.example.hms1.data.models.UserType
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityLoginBinding
import com.example.hms1.ui.admin.AdminDashboardActivity
import com.example.hms1.ui.student.StudentDashboardActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authRepository = AuthRepository()
    private var selectedUserType = UserType.STUDENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUserTypeSpinner()
        setupLoginButton()
        setupSignupButton()
    }

    private fun setupUserTypeSpinner() {
        val userTypes = UserType.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUserType.setAdapter(adapter)

        binding.spinnerUserType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedUserType = UserType.values()[position]
                updateLoginFields()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateLoginFields() {
        when (selectedUserType) {
            UserType.STUDENT -> {
                binding.tilEmail.hint = "Registration Number"
                binding.tilPassword.hint = "Date of Birth (DDMMMYY)"
                binding.btnSignUp.visibility = View.GONE
            }
            UserType.ADMIN -> {
                binding.tilEmail.hint = "Email"
                binding.tilPassword.hint = "Password"
                binding.btnSignUp.visibility = View.VISIBLE
            }
            UserType.WORKER -> {
                binding.tilEmail.hint = "Worker ID"
                binding.tilPassword.hint = "Password"
                binding.btnSignUp.visibility = View.GONE
            }
        }
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = when (selectedUserType) {
                    UserType.STUDENT -> authRepository.loginStudent(email, password)
                    UserType.ADMIN -> authRepository.loginAdmin(email, password)
                    UserType.WORKER -> authRepository.loginWorker(email, password)
                }

                result.fold(
                    onSuccess = { user ->
                        when (user.userType) {
                            UserType.STUDENT -> {
                                startActivity(Intent(this@LoginActivity, StudentDashboardActivity::class.java).apply {
                                    putExtra("USER_ID", user.id)
                                })
                                finish()
                            }
                            UserType.ADMIN -> {
                                startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java).apply {
                                    putExtra("USER_ID", user.id)
                                })
                                finish()
                            }
                            UserType.WORKER -> {
                                // TODO: Navigate to worker dashboard
                                showError("Worker dashboard not implemented yet")
                            }
                        }
                    },
                    onFailure = { exception ->
                        showError(exception.message ?: "Login failed")
                    }
                )
            }
        }
    }

    private fun setupSignupButton() {
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, AdminSignupActivity::class.java))
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 