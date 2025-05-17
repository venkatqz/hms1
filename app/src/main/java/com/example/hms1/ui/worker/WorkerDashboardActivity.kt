package com.example.hms1.ui.worker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.repository.ComplaintRepository
import com.example.hms1.databinding.ActivityWorkerDashboardBinding
import com.example.hms1.ui.complaints.ComplaintAdapter
import kotlinx.coroutines.launch

class WorkerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkerDashboardBinding
    private val complaintRepository = ComplaintRepository()
    private lateinit var complaintAdapter: ComplaintAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadComplaints()
    }

    private fun setupRecyclerView() {
        complaintAdapter = ComplaintAdapter(
            onItemClick = { complaint ->
                // Handle complaint item click
                Toast.makeText(this, "Complaint clicked: ${complaint.title}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.rvComplaints.apply {
            layoutManager = LinearLayoutManager(this@WorkerDashboardActivity)
            adapter = complaintAdapter
        }
    }

    private fun loadComplaints() {
        lifecycleScope.launch {
            try {
                // TODO: Get worker's assigned complaints
                val result = complaintRepository.getComplaintsByStatus(ComplaintStatus.PENDING)
                result.onSuccess { complaints ->
                    complaintAdapter.submitList(complaints)
                }.onFailure { error ->
                    Toast.makeText(this@WorkerDashboardActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@WorkerDashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 