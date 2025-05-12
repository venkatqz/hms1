package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.Complaint
import com.example.hms1.databinding.FragmentComplaintsBinding
import com.example.hms1.ui.complaints.ComplaintAdapter

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
    }

    private fun setupRecyclerView() {
        complaintAdapter = ComplaintAdapter(
            onItemClick = { complaint ->
                // Handle complaint click
            },
            onStatusClick = { complaint ->
                // Handle status update click
            }
        )

        binding.rvComplaints.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = complaintAdapter
        }
    }

    fun updateComplaints(complaints: List<Complaint>) {
        complaintAdapter.submitList(complaints)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 