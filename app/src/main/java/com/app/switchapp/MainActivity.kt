package com.app.switchapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.switchapp.databinding.ActivityMainBinding
import com.app.switchapp.presentation.HappinessFragment
import com.app.switchapp.presentation.KindnessFragment
import com.app.switchapp.presentation.MainFragment
import com.app.switchapp.presentation.OptimismFragment
import com.app.switchapp.presentation.RespectFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        // Varsayılan fragment ayarlama (Ana ekran fragmenti)
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
}
