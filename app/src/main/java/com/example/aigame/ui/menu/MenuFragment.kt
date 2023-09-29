package com.example.aigame.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.databinding.FragmentMenuBinding
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvMainMoney.text = it.toString()
            }
        }
        binding.btnHomeShop.setOnClickListener {
            navigationViewModel.loadState(Navigation.SHOP)
        }
        binding.btnHomeInfo.setOnClickListener {
            navigationViewModel.loadState(Navigation.INFO)
        }
        binding.btnHomeSimulator.setOnClickListener {
            navigationViewModel.loadState(Navigation.DIFFICULTY)
        }
        binding.btnHomeWheel.setOnClickListener {
           navigationViewModel.loadState(Navigation.WHEEL)
        }
        return binding.root
    }

}