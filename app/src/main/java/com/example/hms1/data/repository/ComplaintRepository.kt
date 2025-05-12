package com.example.hms1.data.repository

import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.data.models.WorkerType
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.Date

class ComplaintRepository {
    private val db = FirebaseFirestore.getInstance()
    private val complaintsCollection = db.collection("complaints")

    // TODO: Replace with actual database implementation
    private val complaints = mutableListOf<Complaint>()

    fun getComplaintsByStudent(
        studentId: String,
        onSuccess: (List<Complaint>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val studentComplaints = complaints.filter { it.studentId == studentId }
            onSuccess(studentComplaints)
        } catch (e: Exception) {
            onError(e.message ?: "Failed to load complaints")
        }
    }

    fun getComplaintsByType(
        workerType: WorkerType,
        onSuccess: (List<Complaint>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val typeComplaints = complaints.filter { it.type.name == workerType.name }
            onSuccess(typeComplaints)
        } catch (e: Exception) {
            onError(e.message ?: "Failed to load complaints")
        }
    }

    suspend fun getComplaintsForStudent(studentId: String): Result<List<Complaint>> {
        return try {
            val snapshot = complaintsCollection
                .whereEqualTo("studentId", studentId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val complaints = snapshot.toObjects(Complaint::class.java)
            Result.success(complaints)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createComplaint(complaint: Complaint): Result<Complaint> {
        return try {
            val docRef = complaintsCollection.document()
            val newComplaint = complaint.copy(
                id = docRef.id,
                createdAt = Date(),
                updatedAt = Date()
            )
            docRef.set(newComplaint).await()
            Result.success(newComplaint)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getComplaintsForBlock(block: String): Result<List<Complaint>> {
        return try {
            val snapshot = complaintsCollection
                .whereEqualTo("block", block)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val complaints = snapshot.toObjects(Complaint::class.java)
            Result.success(complaints)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateComplaintStatus(
        complaintId: String,
        status: ComplaintStatus,
        notes: String? = null
    ): Result<Complaint> {
        return try {
            val complaintDoc = complaintsCollection.document(complaintId)
            val updates = hashMapOf<String, Any>(
                "status" to status,
                "updatedAt" to Date()
            )
            if (notes != null) {
                updates["notes"] = notes
            }
            complaintDoc.update(updates).await()
            
            val updatedComplaint = complaintDoc.get().await().toObject(Complaint::class.java)
                ?: throw Exception("Complaint not found")
            Result.success(updatedComplaint)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun assignWorker(
        complaintId: String,
        workerId: String
    ): Result<Complaint> {
        return try {
            val complaintDoc = complaintsCollection.document(complaintId)
            val updates = hashMapOf<String, Any>(
                "assignedWorkerId" to workerId,
                "updatedAt" to Date()
            )
            complaintDoc.update(updates).await()
            
            val updatedComplaint = complaintDoc.get().await().toObject(Complaint::class.java)
                ?: throw Exception("Complaint not found")
            Result.success(updatedComplaint)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getComplaintsByType(type: ComplaintType): Result<List<Complaint>> {
        return try {
            val snapshot = complaintsCollection
                .whereEqualTo("type", type)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val complaints = snapshot.toObjects(Complaint::class.java)
            Result.success(complaints)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getComplaintsByStatus(status: ComplaintStatus): Result<List<Complaint>> {
        return try {
            val snapshot = complaintsCollection
                .whereEqualTo("status", status)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val complaints = snapshot.toObjects(Complaint::class.java)
            Result.success(complaints)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 