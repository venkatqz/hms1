package com.example.hms1.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.R
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityStudentSignupBinding
import com.example.hms1.ui.student.StudentDashboardActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StudentSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentSignupBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSignupButton()
    }

    private fun setupSignupButton() {
        binding.btnSignup.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()
            val roomNumber = binding.etRoomNumber.text.toString()
            val block = findViewById<TextInputEditText>(R.id.etBlock).text.toString()
            val phoneNumber = findViewById<TextInputEditText>(R.id.etPhoneNumber).text.toString()
            val dateOfBirthStr = binding.etDateOfBirth.text.toString()

            if (email.isBlank() || password.isBlank() || name.isBlank() || 
                roomNumber.isBlank() || block.isBlank() || phoneNumber.isBlank() || 
                dateOfBirthStr.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Parse date string to Date object
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateOfBirth = try {
                dateFormat.parse(dateOfBirthStr) ?: Date()
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid date format. Use DD/MM/YYYY", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val result = authRepository.signUpStudent(
                        email = email,
                        password = password,
                        name = name,
                        roomNumber = roomNumber,
                        block = block,
                        phoneNumber = phoneNumber,
                        dateOfBirth = dateOfBirth
                    )
                    result.onSuccess {
                        Toast.makeText(this@StudentSignupActivity, "Signup successful!", Toast.LENGTH_SHORT).show()
                        startActivity(android.content.Intent(this@StudentSignupActivity, StudentDashboardActivity::class.java))
                        finish()
                    }.onFailure { error ->
                        Toast.makeText(this@StudentSignupActivity, "Signup failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@StudentSignupActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
} 