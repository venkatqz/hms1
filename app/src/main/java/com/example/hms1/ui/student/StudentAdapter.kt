package com.example.hms1.ui.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hms1.data.models.User
import com.example.hms1.databinding.ItemStudentBinding

class StudentAdapter(
    private val onItemClick: (User.Student) -> Unit
) : ListAdapter<User.Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(student: User.Student) {
            binding.apply {
                tvName.text = student.name
                tvEmail.text = student.email
                tvRoom.text = student.roomNumber
                tvBlock.text = student.block
            }
        }
    }

    private class StudentDiffCallback : DiffUtil.ItemCallback<User.Student>() {
        override fun areItemsTheSame(oldItem: User.Student, newItem: User.Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User.Student, newItem: User.Student): Boolean {
            return oldItem == newItem
        }
    }
} 