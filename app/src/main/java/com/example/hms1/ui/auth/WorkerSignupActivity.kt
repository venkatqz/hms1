package com.example.hms1.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.models.WorkerType
import com.example.hms1.data.models.UserType
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityWorkerSignupBinding
import kotlinx.coroutines.launch

class WorkerSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkerSignupBinding
    private val authRepository = AuthRepository()
    private var selectedWorkerType = WorkerType.ELECTRICAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWorkerTypeSpinner()
        setupSignupButton()
    }

    private fun setupWorkerTypeSpinner() {
        val workerTypes = WorkerType.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, workerTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerWorkerType.adapter = adapter

        binding.spinnerWorkerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWorkerType = WorkerType.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Keep the default selection
            }
        }
    }

    private fun setupSignupButton() {
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val workerId = binding.etWorkerId.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (name.isEmpty() || workerId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showError("Passwords do not match")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = authRepository.createUser(
                    name = name,
                    email = workerId,
                    password = password,
                    userType = UserType.WORKER,
                    additionalData = mapOf(
                        "workerId" to workerId,
                        "workerType" to selectedWorkerType.name
                    )
                )

                result.fold(
                    onSuccess = {
                        showSuccess("Worker account created successfully")
                        finish()
                    },
                    onFailure = { exception ->
                        showError(exception.message ?: "Failed to create worker account")
                    }
                )
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 