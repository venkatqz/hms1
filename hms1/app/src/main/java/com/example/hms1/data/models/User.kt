package com.example.hms1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserType {
    STUDENT,
    ADMIN,
    WORKER
}

enum class WorkerType {
    ELECTRICAL,
    PLUMBING,
    CARPENTER,
    CIVIL,
    CLEANING
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val userType: UserType,
    val password: String,
    val registrationNumber: String? = null,
    val roomNumber: String? = null,
    val dateOfBirth: String? = null,
    val block: String? = null,
    val workerId: String? = null,
    val workerType: WorkerType? = null
) 