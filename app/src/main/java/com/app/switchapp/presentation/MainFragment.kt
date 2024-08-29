package com.app.switchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.app.switchapp.MainActivity
import com.app.switchapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val switchList = mutableListOf<Switch>()

    private val viewModel: MainViewModel by activityViewModels()

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
        viewModel.isEgoChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchEgo.isChecked = isChecked
            updateSwitchesState(isChecked)
        })

        viewModel.isBottomNavVisible.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) {
                (activity as MainActivity).showBottomNavigation()
            } else {
                (activity as MainActivity).hideBottomNavigation()
            }
        })

        viewModel.switchStates.observe(viewLifecycleOwner, Observer { switchStates ->
            switchStates.forEach { (switchName, isChecked) ->
                val switch = switchList.find { it.text.toString() == switchName }
                switch?.isChecked = isChecked
            }
        })

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setEgoChecked(isChecked)
        }

        switchList.drop(1).forEach { switch ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateSwitchState(switch.text.toString(), isChecked)
                if (isChecked) {
                    (activity as MainActivity).addItemToBottomNav(switch.text.toString())
                } else {
                    (activity as MainActivity).removeItemFromBottomNav(switch.text.toString())
                }
            }
        }
    }

    private fun updateSwitchesState(isEgoChecked: Boolean) {
        switchList.drop(1).forEach { it.isEnabled = !isEgoChecked }
    }
}
