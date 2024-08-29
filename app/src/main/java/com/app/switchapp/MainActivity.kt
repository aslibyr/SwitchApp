package com.app.switchapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.switchapp.databinding.ActivityMainBinding
import com.app.switchapp.presentation.HappinessFragment
import com.app.switchapp.presentation.KindnessFragment
import com.app.switchapp.presentation.MainFragment
import com.app.switchapp.presentation.MainViewModel
import com.app.switchapp.presentation.OptimismFragment
import com.app.switchapp.presentation.RespectFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels() // ViewModel'ı Activity'e bağlıyoruz
    private val menuItems = mutableListOf<String>()

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

                else -> false
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()

        viewModel.isBottomNavVisible.observe(this) { isVisible ->
            if (isVisible) {
                showBottomNavigation()
            } else {
                hideBottomNavigation()
            }
        }
    }

    fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun addItemToBottomNav(title: String) {
        if (!menuItems.contains(title) && menuItems.size < 4) {
            menuItems.add(title)
            updateBottomNav()
        }
    }

    fun removeItemFromBottomNav(title: String) {
        if (menuItems.contains(title)) {
            menuItems.remove(title)
            updateBottomNav()
        }
    }

    private fun updateBottomNav() {
        val menu = binding.bottomNavigationView.menu
        menu.clear()
        menu.add(0, R.id.home, 0, "Ana Ekran")

        menuItems.forEachIndexed { index, item ->
            menu.add(0, View.generateViewId(), index + 1, item)
        }
    }
}
