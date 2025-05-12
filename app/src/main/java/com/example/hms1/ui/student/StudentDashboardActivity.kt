package com.example.hms1.ui.student

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.R
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.data.models.User
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.data.repository.ComplaintRepository
import com.example.hms1.databinding.ActivityStudentDashboardBinding
import com.example.hms1.databinding.DialogNewComplaintBinding
import com.example.hms1.ui.complaints.ComplaintAdapter
import kotlinx.coroutines.launch

class StudentDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDashboardBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var currentUser: User.Student
    private val complaintRepository = ComplaintRepository()
    private lateinit var complaintAdapter: ComplaintAdapter
    private lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)

        authRepository = AuthRepository()
        setupRecyclerView()
        loadUserData()
        setupFab()
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        complaintAdapter = ComplaintAdapter(
            onItemClick = { complaint ->
                Toast.makeText(this, "Complaint clicked: ${complaint.title}", Toast.LENGTH_SHORT).show()
            }
        )
        
        binding.rvComplaints.apply {
            this.layoutManager = this@StudentDashboardActivity.layoutManager
            adapter = this@StudentDashboardActivity.complaintAdapter
        }
    }

    private fun setupFab() {
        binding.fabNewComplaint.setOnClickListener {
            showNewComplaintDialog()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                val currentUser = authRepository.getCurrentUser()
                if (currentUser != null) {
                    val userData = authRepository.getUserData(currentUser.uid)
                    if (userData is User.Student) {
                        this@StudentDashboardActivity.currentUser = userData
                        supportActionBar?.title = "Welcome, ${userData.name}"
                        loadComplaints()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@StudentDashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadComplaints() {
        lifecycleScope.launch {
            try {
                val result = complaintRepository.getComplaintsForStudent(currentUser.id)
                result.onSuccess { complaints ->
                    complaintAdapter.submitList(complaints)
                }.onFailure { error ->
                    Toast.makeText(this@StudentDashboardActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@StudentDashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNewComplaintDialog() {
        val dialogBinding = DialogNewComplaintBinding.inflate(LayoutInflater.from(this))
        
        AlertDialog.Builder(this)
            .setTitle("New Complaint")
            .setView(dialogBinding.root)
            .setPositiveButton("Submit") { _, _ ->
                val title = dialogBinding.etTitle.text.toString()
                val description = dialogBinding.etDescription.text.toString()
                
                if (title.isBlank() || description.isBlank()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val complaint = Complaint(
                    studentId = currentUser.id,
                    title = title,
                    description = description,
                    status = ComplaintStatus.PENDING,
                    roomNumber = currentUser.roomNumber,
                    block = currentUser.block,
                    type = ComplaintType.GENERAL
                )

                lifecycleScope.launch {
                    try {
                        complaintRepository.createComplaint(complaint)
                        Toast.makeText(this@StudentDashboardActivity,
                            "Complaint submitted successfully",
                            Toast.LENGTH_SHORT).show()
                        loadComplaints()
                    } catch (e: Exception) {
                        Toast.makeText(this@StudentDashboardActivity,
                            "Failed to submit complaint: ${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}