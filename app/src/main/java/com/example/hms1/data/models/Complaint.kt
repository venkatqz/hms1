package com.example.hms1.data.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Complaint(
    @DocumentId
    val id: String = "",
    val studentId: String = "",
    val title: String = "",
    val description: String = "",
    val status: ComplaintStatus = ComplaintStatus.PENDING,
    val roomNumber: String = "",
    val block: String = "",
    val type: ComplaintType = ComplaintType.GENERAL,
    val assignedWorkerId: String? = null,
    val notes: String? = null,
    @ServerTimestamp
    val createdAt: Date = Date(),
    @ServerTimestamp
    val updatedAt: Date = Date()
)

enum class ComplaintStatus {
    PENDING,
    IN_PROGRESS,
    RESOLVED,
    REJECTED
}

enum class ComplaintType {
    GENERAL,
    MAINTENANCE,
    CLEANING,
    SECURITY,
    OTHER
} 