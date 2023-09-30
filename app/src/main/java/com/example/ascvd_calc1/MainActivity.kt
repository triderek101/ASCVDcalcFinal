package com.example.ascvd_calc1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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

        // Inside your onCreate method, after setting up the NavController:

// Handle BottomNavigationView clicks
        binding.bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // If you are already on the HomeFragment, don't do anything
                    if (navController.currentDestination?.id == R.id.navigation_home) {
                        // Do something if needed, or simply return true
                        true
                    } else {
                        // Else navigate to the HomeFragment
                        navController.navigate(R.id.navigation_home)
                        true
                    }
                }
                else -> NavigationUI.onNavDestinationSelected(menuItem, navController)
            }
        }

        navController.popBackStack(R.id.navigation_home, false)

        // Setup BottomNavigationView with NavController
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        // Log for each navigation item click
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_home -> {
                    Log.d("NAV", "Home was clicked!")
                }
                R.id.navigation_about -> {
                    Log.d("NAV", "About was clicked!")
                }
                R.id.navigation_disclaimer -> {
                    Log.d("NAV", "Disclaimer was clicked!")
                }
                R.id.navigation_references -> {
                    Log.d("NAV", "References was clicked!")
                }
            }
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            true
        }

        /* // Handle the toggle switch for the calculators
        binding.toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                navController.navigate(R.id.asvcd2018Calc)
            } else {
                navController.navigate(R.id.asvcd2013Calc)
            }
        } */
    }

    // Ensure the system back button in the toolbar works correctly with the NavController
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
