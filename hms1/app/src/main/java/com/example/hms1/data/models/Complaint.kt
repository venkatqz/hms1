package com.example.hms1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class ComplaintType {
    ELECTRICAL,
    PLUMBING,
    CARPENTER,
    CIVIL,
    CLEANING,
    OTHER
}

enum class ComplaintStatus {
    PENDING,
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

@Entity(tableName = "complaints")
data class Complaint(
    @PrimaryKey
    val id: String,
    val studentId: String,
    val roomNumber: String,
    val block: String,
    val type: ComplaintType,
    val description: String,
    val status: ComplaintStatus,
    val assignedWorkerId: String? = null,
    val notes: String? = null,
    val createdAt: Date,
    val updatedAt: Date
) 