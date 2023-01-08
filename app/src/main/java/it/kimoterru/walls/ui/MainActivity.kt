package it.kimoterru.walls.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.ActivityMainBinding
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> showBottomNav()
                R.id.fragment_saved -> showBottomNav()
                R.id.fragment_profile -> showBottomNav()

                R.id.fragment_detail_image -> hideBottomNav()
                R.id.fragment_empty_user_profile -> showBottomNav()
                R.id.fragment_settings -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.mainBottomNav.visible()
    }

    private fun hideBottomNav() {
        binding.mainBottomNav.gone()
    }
}