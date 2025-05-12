package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.CleaningTask
import com.example.hms1.databinding.FragmentCleaningBinding
import com.example.hms1.ui.cleaning.CleaningAdapter

class CleaningFragment : Fragment() {
    private var _binding: FragmentCleaningBinding? = null
    private val binding get() = _binding!!
    private lateinit var cleaningAdapter: CleaningAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCleaningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        cleaningAdapter = CleaningAdapter(
            onItemClick = { task ->
                // Handle task click
            },
            onStatusClick = { task ->
                // Handle status update click
            }
        )

        binding.rvCleaning.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cleaningAdapter
        }
    }

    private fun loadDummyData() {
        val dummyTasks = listOf(
            CleaningTask(
                id = "1",
                block = "Block A",
                floor = "1st Floor",
                status = "PENDING",
                assignedWorkerId = null,
                createdAt = null,
                updatedAt = null
            ),
            CleaningTask(
                id = "2",
                block = "Block A",
                floor = "2nd Floor",
                status = "IN_PROGRESS",
                assignedWorkerId = "worker1",
                createdAt = null,
                updatedAt = null
            ),
            CleaningTask(
                id = "3",
                block = "Block B",
                floor = "1st Floor",
                status = "COMPLETED",
                assignedWorkerId = "worker2",
                createdAt = null,
                updatedAt = null
            )
        )
        cleaningAdapter.submitList(dummyTasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 