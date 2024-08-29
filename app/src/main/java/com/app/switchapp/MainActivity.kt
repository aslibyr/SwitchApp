package com.app.switchapp

import MainFragment
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.switchapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val menuItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView: BottomNavigationView = binding.bottomNavigationView

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()

    }


    fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun addItemToBottomNav(title: String) {
        if (menuItems.size < 4) {
            menuItems.add(title)
            updateBottomNav()
        }
    }

    fun removeItemFromBottomNav(title: String) {
        menuItems.remove(title)
        updateBottomNav()
    }

    private fun updateBottomNav() {
        binding.bottomNavigationView.menu.clear()
        menuItems.forEachIndexed { index, item ->
            binding.bottomNavigationView.menu.add(0, index, 0, item)
        }
    }
}
