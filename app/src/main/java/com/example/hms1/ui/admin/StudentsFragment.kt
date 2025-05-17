package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hms1.data.models.User
import com.example.hms1.databinding.FragmentStudentsBinding
import com.example.hms1.ui.student.StudentAdapter

class StudentsFragment : Fragment() {
    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter { _ ->
            // Handle student item click
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@StudentsFragment.adapter
        }
    }

    private fun loadDummyData() {
        val students = listOf(
            User.Student(
                id = "1",
                name = "John Doe",
                email = "john@example.com",
                roomNumber = "101",
                block = "A"
            ),
            User.Student(
                id = "2",
                name = "Jane Smith",
                email = "jane@example.com",
                roomNumber = "102",
                block = "A"
            ),
            User.Student(
                id = "3",
                name = "Mike Johnson",
                email = "mike@example.com",
                roomNumber = "201",
                block = "B"
            )
        )
        adapter.submitList(students)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 