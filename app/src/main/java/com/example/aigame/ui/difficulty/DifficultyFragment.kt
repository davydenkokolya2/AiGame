package com.example.aigame.ui.difficulty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.databinding.FragmentDifficultyBinding
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DifficultyFragment : Fragment() {

    private lateinit var binding: FragmentDifficultyBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDifficultyBinding.inflate(inflater, container, false)
        binding.btnDifficultyBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        binding.btnDifficultySmallRoom.setOnClickListener {
            navigationViewModel.loadState(Navigation.SMALL)
        }
        binding.btnDifficultyMediumRoom.setOnClickListener {
            navigationViewModel.loadState(Navigation.MEDIUM)
        }
        binding.btnDifficultyBigRoom.setOnClickListener {
            navigationViewModel.loadState(Navigation.BIG)
        }
        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvDifficultyMoney.text = it.toString()
            }
        }
        return binding.root
    }

}