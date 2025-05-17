package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.databinding.FragmentComplaintsBinding
import com.example.hms1.ui.complaints.ComplaintAdapter
import java.util.Date

class ComplaintsFragment : Fragment() {
    private var _binding: FragmentComplaintsBinding? = null
    private val binding get() = _binding!!
    private lateinit var complaintAdapter: ComplaintAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplaintsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        complaintAdapter = ComplaintAdapter(
            onItemClick = { complaint ->
                // Handle complaint click
                updateComplaintStatus(complaint)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = complaintAdapter
        }
    }

    private fun updateComplaintStatus(complaint: Complaint) {
        val newStatus = when (complaint.status) {
            ComplaintStatus.PENDING -> ComplaintStatus.IN_PROGRESS
            ComplaintStatus.IN_PROGRESS -> ComplaintStatus.RESOLVED
            ComplaintStatus.RESOLVED -> ComplaintStatus.PENDING
            ComplaintStatus.REJECTED -> ComplaintStatus.PENDING
        }
        
        // Update the complaint in the adapter
        val updatedComplaint = complaint.copy(
            status = newStatus,
            updatedAt = Date()
        )
        
        // Update the list
        val currentList = complaintAdapter.currentList.toMutableList()
        val index = currentList.indexOfFirst { it.id == complaint.id }
        if (index != -1) {
            currentList[index] = updatedComplaint
            complaintAdapter.submitList(currentList)
        }
    }

    private fun loadDummyData() {
        val dummyComplaints = listOf(
            Complaint(
                id = "1",
                title = "Water Leak",
                description = "Water leaking from ceiling",
                status = ComplaintStatus.PENDING,
                type = ComplaintType.MAINTENANCE,
                roomNumber = "101",
                block = "A",
                createdAt = Date(),
                updatedAt = Date()
            ),
            Complaint(
                id = "2",
                title = "AC Not Working",
                description = "Air conditioner not cooling properly",
                status = ComplaintStatus.IN_PROGRESS,
                type = ComplaintType.MAINTENANCE,
                roomNumber = "102",
                block = "A",
                createdAt = Date(),
                updatedAt = Date()
            )
        )
        complaintAdapter.submitList(dummyComplaints)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 