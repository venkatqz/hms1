package com.example.hms1.ui.cleaning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hms1.data.models.CleaningTask
import com.example.hms1.databinding.ItemCleaningBinding

class CleaningAdapter(
    private val onItemClick: (CleaningTask) -> Unit,
    private val onStatusClick: (CleaningTask) -> Unit
) : ListAdapter<CleaningTask, CleaningAdapter.CleaningViewHolder>(CleaningDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningViewHolder {
        val binding = ItemCleaningBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CleaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CleaningViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CleaningViewHolder(
        private val binding: ItemCleaningBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
            binding.btnStatus.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onStatusClick(getItem(position))
                }
            }
        }

        fun bind(task: CleaningTask) {
            binding.apply {
                tvBlock.text = task.block
                tvFloor.text = task.floor
                tvStatus.text = task.status.name
                tvWorker.text = if (task.assignedWorker.isNotEmpty()) task.assignedWorker else "Not Assigned"
            }
        }
    }

    private class CleaningDiffCallback : DiffUtil.ItemCallback<CleaningTask>() {
        override fun areItemsTheSame(oldItem: CleaningTask, newItem: CleaningTask): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CleaningTask, newItem: CleaningTask): Boolean {
            return oldItem == newItem
        }
    }
} 