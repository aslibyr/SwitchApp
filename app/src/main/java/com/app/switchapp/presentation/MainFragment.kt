package com.app.switchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.app.switchapp.MainActivity
import com.app.switchapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding // ViewBinding kullanıyoruz
    private val switchList = mutableListOf<Switch>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        switchList.add(binding.switchEgo)
        switchList.add(binding.giving)
        switchList.add(binding.respect)
        switchList.add(binding.kindness)
        switchList.add(binding.optimism)
        switchList.add(binding.happiness)

        setupSwitches()

        return binding.root
    }

    private fun setupSwitches() {
        binding.switchEgo.isChecked = true
        updateSwitchesState(binding.switchEgo.isChecked)

        binding.switchEgo.setOnCheckedChangeListener { buttonView, isChecked ->
            updateSwitchesState(isChecked)
            if (!isChecked) {
                (activity as MainActivity).hideBottomNavigation()
            } else {
                (activity as MainActivity).showBottomNavigation()
            }
        }

        switchList.drop(1).forEach { switch ->
            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    (activity as MainActivity)
                } else {
                    (activity as MainActivity)
                }
            }
        }
    }

    private fun updateSwitchesState(isEgoChecked: Boolean) {
        switchList.drop(1).forEach { it.isEnabled = !isEgoChecked }
    }
}
