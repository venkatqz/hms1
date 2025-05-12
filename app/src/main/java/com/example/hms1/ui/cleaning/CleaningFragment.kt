package com.example.hms1.ui.cleaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.CleaningTask
import com.example.hms1.data.models.CleaningStatus
import com.example.hms1.databinding.FragmentCleaningBinding
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
                // For documentation, just show a toast
                android.widget.Toast.makeText(
                    requireContext(),
                    "Task clicked: ${task.block} - Floor ${task.floor}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            },
            onStatusClick = { task ->
                // For documentation, just show a toast
                android.widget.Toast.makeText(
                    requireContext(),
                    "Status clicked for: ${task.block} - Floor ${task.floor}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cleaningAdapter
        }
    }

    private fun loadDummyData() {
        val dummyTasks = listOf(
            CleaningTask(
                id = "1",
                block = "Block A",
                floor = "1",
                status = CleaningStatus.PENDING,
                assignedWorker = "Worker 1",
                createdAt = Date(),
                updatedAt = Date()
            ),
            CleaningTask(
                id = "2",
                block = "Block B",
                floor = "2",
                status = CleaningStatus.IN_PROGRESS,
                assignedWorker = "Worker 2",
                createdAt = Date(),
                updatedAt = Date()
            ),
            CleaningTask(
                id = "3",
                block = "Block C",
                floor = "3",
                status = CleaningStatus.COMPLETED,
                assignedWorker = "Worker 3",
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