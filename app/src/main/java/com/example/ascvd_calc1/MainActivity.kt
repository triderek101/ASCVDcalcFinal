package com.example.ascvd_calc1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ascvd_calc1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate executed")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        // Setup BottomNavigationView with NavController
        binding.bottomNavView.setupWithNavController(navController)

        // Log for each navigation item click
        binding.bottomNavView.setOnItemReselectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_home -> Log.d("NAV", "Home was clicked!")
                R.id.navigation_about -> Log.d("NAV", "About was clicked!")
                R.id.navigation_disclaimer -> Log.d("NAV", "Disclaimer was clicked!")
                R.id.navigation_references -> Log.d("NAV", "References was clicked!")
                R.id.navigation_moreinfo -> Log.d("NAV", "MoreInfo was clicked!")
            }
        }

        // User Agreement Dialog
        showUserAgreementDialog()
    }

    // Ensure the system back button in the toolbar works correctly with the NavController
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun showUserAgreementDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("*For Clinicans Only*")
        builder.setMessage("This app was designed to be for clinicans only. Non-clinicans should seek advice from their physician")
        builder.setCancelable(false)

        // Positive button (Agree)
        builder.setPositiveButton("Agree") { dialog, _ ->
            // Continue using the app
            dialog.dismiss()
        }

        // Negative button (Disagree)
        builder.setNegativeButton("Disagree") { _, _ ->
            // Close the app
            finish()
        }

        // Show the dialog
        builder.show()
    }
}
