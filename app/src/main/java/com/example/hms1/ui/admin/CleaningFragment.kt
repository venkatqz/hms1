package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.CleaningTask
import com.example.hms1.data.models.CleaningStatus
import com.example.hms1.databinding.FragmentCleaningBinding
import com.example.hms1.ui.cleaning.CleaningAdapter
import java.util.Date

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
                updateTaskStatus(task)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cleaningAdapter
        }
    }

    private fun updateTaskStatus(task: CleaningTask) {
        val newStatus = when (task.status) {
            CleaningStatus.PENDING -> CleaningStatus.IN_PROGRESS
            CleaningStatus.IN_PROGRESS -> CleaningStatus.COMPLETED
            CleaningStatus.COMPLETED -> CleaningStatus.PENDING
        }
        
        // Update the task in the adapter
        val updatedTask = task.copy(
            status = newStatus,
            updatedAt = Date()
        )
        
        // Update the list
        val currentList = cleaningAdapter.currentList.toMutableList()
        val index = currentList.indexOfFirst { it.id == task.id }
        if (index != -1) {
            currentList[index] = updatedTask
            cleaningAdapter.submitList(currentList)
        }
    }

    private fun loadDummyData() {
        val dummyTasks = listOf(
            CleaningTask(
                id = "1",
                block = "Block A",
                floor = "1st Floor",
                status = CleaningStatus.PENDING,
                assignedWorker = "",
                createdAt = Date(),
                updatedAt = Date()
            ),
            CleaningTask(
                id = "2",
                block = "Block A",
                floor = "2nd Floor",
                status = CleaningStatus.IN_PROGRESS,
                assignedWorker = "worker1",
                createdAt = Date(),
                updatedAt = Date()
            ),
            CleaningTask(
                id = "3",
                block = "Block B",
                floor = "1st Floor",
                status = CleaningStatus.COMPLETED,
                assignedWorker = "worker2",
                createdAt = Date(),
                updatedAt = Date()
            )
        )
        cleaningAdapter.submitList(dummyTasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 