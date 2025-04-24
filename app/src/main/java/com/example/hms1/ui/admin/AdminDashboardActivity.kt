package com.example.hms1.ui.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.models.User
import com.example.hms1.data.models.UserType
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityAdminDashboardBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDashboardBinding
    private val authRepository = AuthRepository()
    private val dateFormat = SimpleDateFormat("ddMMMyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAddStudentButton()
    }

    private fun setupAddStudentButton() {
        binding.btnAddStudent.setOnClickListener {
            val name = binding.etName.text.toString()
            val regNo = binding.etRegNo.text.toString()
            val roomNo = binding.etRoomNo.text.toString()
            val dateOfBirth = binding.etDateOfBirth.text.toString()

            if (name.isBlank() || regNo.isBlank() || roomNo.isBlank() || dateOfBirth.isBlank()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            // Validate registration number format (24mx358)
            if (!regNo.matches(Regex("^\\d{2}[a-zA-Z]{2}\\d{3}$"))) {
                showError("Invalid registration number format. Use format: 24mx358")
                return@setOnClickListener
            }

            // Validate date format (DDMMMYY)
            try {
                dateFormat.parse(dateOfBirth)
            } catch (e: Exception) {
                showError("Invalid date format. Use format: DDMMMYY (e.g., 02JAN03)")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = authRepository.createStudent(name, regNo, roomNo, dateOfBirth)
                result.fold(
                    onSuccess = {
                        clearForm()
                        showSuccess("Student added successfully")
                    },
                    onFailure = { exception ->
                        showError(exception.message ?: "Failed to add student")
                    }
                )
            }
        }
    }

    private fun clearForm() {
        binding.apply {
            etName.text?.clear()
            etRegNo.text?.clear()
            etRoomNo.text?.clear()
            etDateOfBirth.text?.clear()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 