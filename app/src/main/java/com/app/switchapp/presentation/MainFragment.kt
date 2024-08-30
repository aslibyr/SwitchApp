package com.app.switchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.switchapp.MainActivity
import com.app.switchapp.R
import com.app.switchapp.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setupSwitches()

        return binding.root
    }

    private fun setupSwitches() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.switchEgo.isChecked = uiState.isEgoChecked
                binding.giving.isChecked = uiState.isGivingChecked
                binding.respect.isChecked = uiState.isRespectChecked
                binding.kindness.isChecked = uiState.isKindnessChecked
                binding.optimism.isChecked = uiState.isOptimismChecked
                binding.happiness.isChecked = uiState.isHappinessChecked
                updateSwitchesState(uiState.isEgoChecked)
                if (uiState.isBottomNavVisible) {
                    (activity as MainActivity).showBottomNavigation()
                } else {
                    (activity as MainActivity).hideBottomNavigation()
                }
            }
        }

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setEgoChecked(isChecked)
        }

        binding.giving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setGivingChecked(isChecked)
            handleBottomNavItem(R.id.giving, isChecked)
        }

        binding.respect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setRespectChecked(isChecked)
            handleBottomNavItem(R.id.respect, isChecked)
        }

        binding.kindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setKindnessChecked(isChecked)
            handleBottomNavItem(R.id.kindness, isChecked)
        }

        binding.optimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setOptimismChecked(isChecked)
            handleBottomNavItem(R.id.optimism, isChecked)
        }

        binding.happiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setHappinessChecked(isChecked)
            handleBottomNavItem(R.id.happiness, isChecked)
        }

    }


    private fun updateSwitchesState(isEgoChecked: Boolean) {
        // Ego switch'i aktifken diğer switch'leri kapalı yap
        binding.giving.isEnabled = !isEgoChecked
        binding.respect.isEnabled = !isEgoChecked
        binding.kindness.isEnabled = !isEgoChecked
        binding.optimism.isEnabled = !isEgoChecked
        binding.happiness.isEnabled = !isEgoChecked
    }

    private fun handleBottomNavItem(itemId: Int, isChecked: Boolean) {
        if (isChecked) {
            (activity as MainActivity).addItemToBottomNav(itemId)
        } else {
            (activity as MainActivity).removeItemFromBottomNav(itemId)
        }
    }
}
