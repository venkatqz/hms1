package com.example.hms1.ui.student

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.data.models.User
import com.example.hms1.databinding.ActivityStudentDashboardBinding
import com.example.hms1.ui.adapters.ComplaintAdapter
import kotlinx.coroutines.launch
import java.util.Date

class StudentDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDashboardBinding
    private lateinit var currentUser: User
    private val complaintAdapter = ComplaintAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Get current user from intent
        setupComplaintForm()
        setupComplaintsList()
        loadComplaints()
    }

    private fun setupComplaintForm() {
        val complaintTypes = ComplaintType.values().map { it.name }
        binding.spinnerComplaintType.setAdapter(
            android.widget.ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                complaintTypes
            )
        )

        binding.btnSubmitComplaint.setOnClickListener {
            val description = binding.etComplaintDescription.text.toString()
            val selectedType = binding.spinnerComplaintType.text.toString()

            if (description.isBlank() || selectedType.isBlank()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            val complaint = Complaint(
                id = System.currentTimeMillis().toString(),
                studentId = currentUser.id,
                roomNumber = currentUser.roomNumber ?: "",
                block = "A", // TODO: Get from user's room
                type = ComplaintType.valueOf(selectedType),
                description = description,
                status = ComplaintStatus.PENDING,
                createdAt = Date(),
                updatedAt = Date()
            )

            // TODO: Save complaint to Firebase
            clearForm()
            showSuccess("Complaint submitted successfully")
            loadComplaints() // Refresh the list
        }
    }

    private fun setupComplaintsList() {
        binding.rvComplaints.apply {
            layoutManager = LinearLayoutManager(this@StudentDashboardActivity)
            adapter = complaintAdapter
        }
    }

    private fun loadComplaints() {
        // TODO: Load complaints from Firebase
        // For now, using mock data
        val mockComplaints = listOf(
            Complaint(
                id = "1",
                studentId = "1",
                roomNumber = "A-101",
                block = "A",
                type = ComplaintType.ELECTRICAL,
                description = "Fan not working",
                status = ComplaintStatus.PENDING,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Complaint(
                id = "2",
                studentId = "1",
                roomNumber = "A-101",
                block = "A",
                type = ComplaintType.PLUMBING,
                description = "Water leakage in bathroom",
                status = ComplaintStatus.IN_PROGRESS,
                notes = "Plumber assigned and working on it",
                createdAt = Date(),
                updatedAt = Date()
            )
        )
        complaintAdapter.submitList(mockComplaints)
    }

    private fun clearForm() {
        binding.etComplaintDescription.text?.clear()
        binding.spinnerComplaintType.text?.clear()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 