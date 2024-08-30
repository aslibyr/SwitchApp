package com.app.switchapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.switchapp.databinding.ActivityMainBinding
import com.app.switchapp.presentation.GivingFragment
import com.app.switchapp.presentation.HappinessFragment
import com.app.switchapp.presentation.KindnessFragment
import com.app.switchapp.presentation.MainFragment
import com.app.switchapp.presentation.MainViewModel
import com.app.switchapp.presentation.OptimismFragment
import com.app.switchapp.presentation.RespectFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    // Menü öğelerinin id'lerini tutmak için bir liste oluşturun.
    private val menuItems = mutableListOf<Int>()
    private val maxMenuItems = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView: BottomNavigationView = binding.bottomNavigationView

        bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment())
                        .commit()
                    true
                }
                R.id.respect -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RespectFragment())
                        .commit()
                    true
                }
                R.id.kindness -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, KindnessFragment())
                        .commit()
                    true
                }
                R.id.optimism -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, OptimismFragment())
                        .commit()
                    true
                }
                R.id.happiness -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HappinessFragment())
                        .commit()
                    true
                }
                R.id.giving -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GivingFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Uygulama açıldığında ana ekranı yükle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()

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
        menu.add(0, R.id.home, 0, "Ana Ekran").setIcon(R.drawable.ic_launcher_foreground)
        viewModel.updateBottomBarCount(menuItems.size)

        menuItems.take(maxMenuItems).forEachIndexed { index, itemId ->
            when (itemId) {
                R.id.respect -> menu.add(0, R.id.respect, index + 1, "Respect")
                    .setIcon(R.drawable.abstract_shape)

                R.id.kindness -> menu.add(0, R.id.kindness, index + 1, "Kindness")
                    .setIcon(R.drawable.abstract_shape3)

                R.id.optimism -> menu.add(0, R.id.optimism, index + 1, "Optimism")
                    .setIcon(R.drawable.abstract_shape2)

                R.id.happiness -> menu.add(0, R.id.happiness, index + 1, "Happiness")
                    .setIcon(R.drawable.abstract_shape1)

                R.id.giving -> menu.add(0, R.id.giving, index + 1, "Giving")
                    .setIcon(R.drawable.teamwork)
            }
        }
    }
}
