package com.example.hms1.ui.complaints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hms1.R
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.databinding.ItemComplaintBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ComplaintAdapter(
    private val onItemClick: (Complaint) -> Unit
) : ListAdapter<Complaint, ComplaintAdapter.ComplaintViewHolder>(ComplaintDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ItemComplaintBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ComplaintViewHolder(
        private val binding: ItemComplaintBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(complaint: Complaint) {
            binding.apply {
                tvTitle.text = complaint.title
                tvDescription.text = complaint.description
                tvStatus.text = complaint.status.name
                tvDate.text = complaint.createdAt?.let { dateFormat.format(it) }
                tvType.text = complaint.type.name
                tvRoomNumber.text = complaint.roomNumber
                tvBlock.text = complaint.block

                // Set status background color
                val statusColor = when (complaint.status) {
                    ComplaintStatus.PENDING -> R.color.status_pending
                    ComplaintStatus.IN_PROGRESS -> R.color.status_in_progress
                    ComplaintStatus.RESOLVED -> R.color.status_resolved
                    ComplaintStatus.REJECTED -> R.color.status_rejected
                }
                tvStatus.setBackgroundResource(statusColor)
            }
        }
    }

    private class ComplaintDiffCallback : DiffUtil.ItemCallback<Complaint>() {
        override fun areItemsTheSame(oldItem: Complaint, newItem: Complaint): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Complaint, newItem: Complaint): Boolean {
            return oldItem == newItem
        }
    }
} 