package com.example.hms1.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hms1.data.repository.AuthRepository
import com.example.hms1.databinding.ActivityAdminSignupBinding
import com.example.hms1.ui.auth.LoginActivity
import kotlinx.coroutines.launch

class AdminSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminSignupBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pre-fill dummy data for documentation
        binding.etName.setText("John Doe")
        binding.etEmail.setText("admin@hms.com")
        binding.etPassword.setText("admin123")
        binding.etBlock.setText("Block A")
        binding.etSecretKey.setText("admin_secret_key")

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSignup.setOnClickListener {
            // For documentation, directly navigate to dashboard
            startActivity(Intent(this, AdminDashboardActivity::class.java))
            finish()
        }
    }
} 