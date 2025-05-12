package com.example.hms1.ui.admin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hms1.R
import com.example.hms1.data.models.Complaint
import com.example.hms1.data.models.ComplaintStatus
import com.example.hms1.data.models.ComplaintType
import com.example.hms1.databinding.ActivityAdminDashboardBinding
import com.example.hms1.ui.complaints.ComplaintAdapter
import com.google.android.material.navigation.NavigationBarView
import java.util.Date

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Admin Dashboard"
        
        setupBottomNavigation()
        // Start with complaints fragment
        loadFragment(ComplaintsFragment())
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_students -> {
                    loadFragment(StudentsFragment())
                    true
                }
                R.id.nav_complaints -> {
                    loadFragment(ComplaintsFragment())
                    true
                }
                R.id.nav_cleaning -> {
                    loadFragment(CleaningFragment())
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        binding.bottomNavigation.selectedItemId = R.id.nav_complaints
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.admin_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // For documentation, just show a toast
                android.widget.Toast.makeText(this, "Settings clicked", android.widget.Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}