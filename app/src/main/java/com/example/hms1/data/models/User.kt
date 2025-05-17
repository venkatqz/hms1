package com.example.hms1.data.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

enum class UserRole {
    STUDENT,
    ADMIN,
    WORKER
}

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

sealed class User {
    abstract val id: String
    abstract val name: String
    abstract val email: String
    abstract val role: UserRole
    abstract val userType: UserType
    abstract val roomNumber: String
    abstract val block: String
    abstract val createdAt: Date

    data class Student(
        @DocumentId
        override val id: String = "",
        override val name: String = "",
        override val email: String = "",
        override val roomNumber: String = "",
        override val block: String = "",
        val phoneNumber: String = "",
        val dateOfBirth: Date = Date(),
        @ServerTimestamp
        override val createdAt: Date = Date()
    ) : User() {
        override val role: UserRole = UserRole.STUDENT
        override val userType: UserType = UserType.STUDENT
    }

    data class Admin(
        @DocumentId
        override val id: String = "",
        override val name: String = "",
        override val email: String = "",
        override val roomNumber: String = "",
        override val block: String = "",
        val secretKey: String = "",
        @ServerTimestamp
        override val createdAt: Date = Date()
    ) : User() {
        override val role: UserRole = UserRole.ADMIN
        override val userType: UserType = UserType.ADMIN
    }

    data class Worker(
        @DocumentId
        override val id: String = "",
        override val name: String = "",
        override val email: String = "",
        val workerType: WorkerType = WorkerType.CLEANING,
        override val roomNumber: String = "",
        override val block: String = "",
        val phoneNumber: String = "",
        val dateOfBirth: Date = Date(),
        @ServerTimestamp
        override val createdAt: Date = Date()
    ) : User() {
        override val role: UserRole = UserRole.WORKER
        override val userType: UserType = UserType.WORKER
    }
} 