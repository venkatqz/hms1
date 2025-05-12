package com.example.hms1.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.databinding.ActivitySetupSecretKeyBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SetupSecretKeyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetupSecretKeyBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupSecretKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSecretKeyButton()
    }

    private fun setupSecretKeyButton() {
        binding.btnSetupSecretKey.setOnClickListener {
            val secretKey = binding.etSecretKey.text.toString()

            if (secretKey.isBlank()) {
                showError("Please enter a secret key")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    db.collection("config")
                        .document("admin_secret")
                        .set(mapOf("key" to secretKey))
                        .await()

                    Toast.makeText(
                        this@SetupSecretKeyActivity,
                        "Secret key set successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } catch (e: Exception) {
                    showError("Failed to set secret key: ${e.message}")
                }
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
} 