package com.app.switchapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.switchapp.databinding.ActivityMainBinding
import com.app.switchapp.presentation.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val menuItems = mutableListOf<Int>()
    private val maxMenuItems = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_graph_main -> {
                    navController.navigate(R.id.nav_graph_main)
                }

                R.id.nav_graph_giving -> {
                    navController.navigate(R.id.nav_graph_giving)
                }

                R.id.nav_graph_respect -> {
                    navController.navigate(R.id.nav_graph_respect)
                }

                R.id.nav_graph_kindness -> {
                    navController.navigate(R.id.nav_graph_kindness)
                }

                R.id.nav_graph_optimism -> {
                    navController.navigate(R.id.nav_graph_optimism)
                }

                R.id.nav_graph_happiness -> {
                    navController.navigate(R.id.nav_graph_happiness)
                }

                else -> {
                    return@setOnItemSelectedListener false
                }

            }

            return@setOnItemSelectedListener false
        }



        // Bottom navigation görünürlüğünü gözlemle
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (uiState.isBottomNavVisible) {
                    showBottomNavigation()
                } else {
                    hideBottomNavigation()
                }
            }
        }
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_graph_main,
                R.id.nav_graph_giving,
                R.id.nav_graph_happiness,
                R.id.nav_graph_respect,
                R.id.nav_graph_kindness,
                R.id.nav_graph_optimism
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun addItemToBottomNav(itemId: Int) {
        if (!menuItems.contains(itemId) && menuItems.size < maxMenuItems) {
            menuItems.add(itemId)
            updateBottomNav()
        }
    }

    fun removeItemFromBottomNav(itemId: Int) {
        if (menuItems.contains(itemId)) {
            menuItems.remove(itemId)
            updateBottomNav()
        }
    }

    private fun updateBottomNav() {
        val menu = binding.bottomNavigationView.menu
        menu.clear()
        menu.add(0, R.id.nav_graph_main, 0, "Ana Ekran").setIcon(R.drawable.ic_launcher_foreground)


        menuItems.take(maxMenuItems).forEachIndexed { index, itemId ->
            when (itemId) {
                R.id.nav_graph_respect -> menu.add(0, R.id.nav_graph_respect, index + 1, "Respect")
                    .setIcon(R.drawable.abstract_shape)

                R.id.nav_graph_kindness -> menu.add(
                    0,
                    R.id.nav_graph_kindness,
                    index + 1,
                    "Kindness"
                )
                    .setIcon(R.drawable.abstract_shape3)

                R.id.nav_graph_optimism -> menu.add(
                    0,
                    R.id.nav_graph_optimism,
                    index + 1,
                    "Optimism"
                )
                    .setIcon(R.drawable.abstract_shape2)

                R.id.nav_graph_happiness -> menu.add(
                    0,
                    R.id.nav_graph_happiness,
                    index + 1,
                    "Happiness"
                )
                    .setIcon(R.drawable.abstract_shape1)

                R.id.nav_graph_giving -> menu.add(0, R.id.nav_graph_giving, index + 1, "Giving")
                    .setIcon(R.drawable.teamwork)
            }
        }
        viewModel.updateBottomBarCount(menuItems.size)
    }
}
