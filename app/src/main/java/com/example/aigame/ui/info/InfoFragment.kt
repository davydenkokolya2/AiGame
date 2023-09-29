package com.example.aigame.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.R
import com.example.aigame.databinding.FragmentInfoBinding
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Info
import com.example.aigame.util.Navigation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val infoViewModel: InfoViewModel by activityViewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvInfoMoney.text = it.toString()
            }
        }
        lifecycleScope.launch {
            infoViewModel.stateInfo.collect {
                binding.btnInfoAbout.setImageResource(R.drawable.icon_about_button_false)
                binding.tvInfoAbout.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvInfoAbout.textSize = 14f
                binding.btnInfoShop.setImageResource(R.drawable.icon_shop_button_false)
                binding.tvInfoShop.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvInfoShop.textSize = 14f
                binding.btnInfoWheel.setImageResource(R.drawable.icon_wheel_button_false)
                binding.tvInfoWheel.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvInfoWheel.textSize = 14f
                binding.btnInfoSimulator.setImageResource(R.drawable.icon_simulator_button_false)
                binding.tvInfoSimulator.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvInfoSimulator.textSize = 14f
                binding.tvInfoTitle.visibility = View.VISIBLE
                binding.tvInfoDescription.textSize = 30f
                when(it) {
                    Info.ABOUT -> {
                        binding.btnInfoAbout.setImageResource(R.drawable.icon_about_button_true)
                        binding.tvInfoAbout.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvInfoAbout.textSize = 22f
                        binding.tvInfoDescription.setText(R.string.info_about_description)
                        binding.tvInfoDescription.textSize = 22f
                        binding.tvInfoTitle.visibility = View.GONE
                    }
                    Info.SHOP -> {
                        binding.btnInfoShop.setImageResource(R.drawable.icon_shop_button_true)
                        binding.tvInfoShop.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvInfoShop.textSize = 22f
                        binding.tvInfoTitle.setText(R.string.info_shop_title)
                        binding.tvInfoDescription.setText(R.string.info_shop_description)
                    }
                    Info.WHEEL -> {
                        binding.btnInfoWheel.setImageResource(R.drawable.icon_wheel_button_true)
                        binding.tvInfoWheel.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvInfoWheel.textSize = 22f
                        binding.tvInfoTitle.setText(R.string.info_wheel_title)
                        binding.tvInfoDescription.setText(R.string.info_wheel_description)
                    }
                    Info.SIMULATOR -> {
                        binding.btnInfoSimulator.setImageResource(R.drawable.icon_simulator_button_true)
                        binding.tvInfoSimulator.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvInfoSimulator.textSize = 22f
                        binding.tvInfoTitle.setText(R.string.info_simulator_title)
                        binding.tvInfoDescription.setText(R.string.info_simulator_description)
                    }
                }
            }
        }

        binding.btnInfoWheel.setOnClickListener {
            infoViewModel.loadState(Info.WHEEL)
        }
        binding.btnInfoShop.setOnClickListener {
            infoViewModel.loadState(Info.SHOP)
        }
        binding.btnInfoSimulator.setOnClickListener {
            infoViewModel.loadState(Info.SIMULATOR)
        }
        binding.btnInfoAbout.setOnClickListener {
            infoViewModel.loadState(Info.ABOUT)
        }
        binding.btnInfoBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        return binding.root
    }


}