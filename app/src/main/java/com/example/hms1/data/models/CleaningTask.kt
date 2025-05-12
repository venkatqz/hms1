package com.example.hms1.data.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class CleaningTask(
    @DocumentId
    val id: String = "",
    val block: String = "",
    val floor: String = "",
    val status: CleaningStatus = CleaningStatus.PENDING,
    val assignedWorker: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) 