package com.example.hms1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hms1.data.models.Complaint
import com.example.hms1.databinding.ItemComplaintBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ComplaintAdapter : ListAdapter<Complaint, ComplaintAdapter.ComplaintViewHolder>(ComplaintDiffCallback()) {

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

    class ComplaintViewHolder(
        private val binding: ItemComplaintBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        fun bind(complaint: Complaint) {
            binding.apply {
                tvComplaintType.text = complaint.type.name
                tvStatus.text = complaint.status.name
                tvDescription.text = complaint.description
                tvRoomNumber.text = "Room: ${complaint.roomNumber}"
                tvDate.text = dateFormat.format(complaint.createdAt)

                if (complaint.notes.isNullOrBlank()) {
                    tvNotes.visibility = View.GONE
                } else {
                    tvNotes.visibility = View.VISIBLE
                    tvNotes.text = complaint.notes
                }

                // Set status color based on complaint status
                tvStatus.setTextColor(
                    when (complaint.status) {
                        ComplaintStatus.PENDING -> android.graphics.Color.parseColor("#FFA500")
                        ComplaintStatus.ASSIGNED -> android.graphics.Color.parseColor("#3498DB")
                        ComplaintStatus.IN_PROGRESS -> android.graphics.Color.parseColor("#E67E22")
                        ComplaintStatus.COMPLETED -> android.graphics.Color.parseColor("#2ECC71")
                        ComplaintStatus.CANCELLED -> android.graphics.Color.parseColor("#E74C3C")
                    }
                )
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