package it.kimoterru.walls.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host)
        binding.mainBottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> showBottomNav()
                R.id.fragment_saved -> showBottomNav()
                R.id.fragment_profile -> showBottomNav()

                R.id.fragment_selected_image -> hideBottomNav()
                R.id.fragment_no_internet -> showBottomNav()
                R.id.fragment_empty_saved -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.mainBottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.mainBottomNav.visibility = View.GONE
    }
}