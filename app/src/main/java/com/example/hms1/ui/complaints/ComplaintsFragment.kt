package com.example.hms1.ui.complaints

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
        complaintAdapter = ComplaintAdapter { complaint ->
            // For documentation, just show a toast
            android.widget.Toast.makeText(
                requireContext(),
                "Complaint clicked: ${complaint.title}",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = complaintAdapter
        }
    }

    private fun loadDummyData() {
        val dummyComplaints = listOf(
            Complaint(
                id = "1",
                studentId = "student1",
                title = "Water Leakage",
                description = "Water is leaking from the ceiling in room 101",
                status = ComplaintStatus.PENDING,
                roomNumber = "101",
                block = "Block A",
                type = ComplaintType.MAINTENANCE,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Complaint(
                id = "2",
                studentId = "student2",
                title = "Electrical Issue",
                description = "Power socket not working in room 203",
                status = ComplaintStatus.IN_PROGRESS,
                roomNumber = "203",
                block = "Block B",
                type = ComplaintType.MAINTENANCE,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Complaint(
                id = "3",
                studentId = "student3",
                title = "Room Cleaning Required",
                description = "Room needs cleaning, not cleaned for 2 days",
                status = ComplaintStatus.RESOLVED,
                roomNumber = "305",
                block = "Block C",
                type = ComplaintType.CLEANING,
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