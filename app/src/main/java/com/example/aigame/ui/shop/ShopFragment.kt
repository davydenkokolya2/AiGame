package com.example.aigame.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aigame.R
import com.example.aigame.databinding.FragmentShopBinding
import com.example.aigame.domain.ShopCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.ShopPopUp_pop_up.ShopPopUpViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.ui.shop_pop_up.ShopPopUpFragment
import com.example.aigame.util.Constant
import com.example.aigame.util.Navigation
import com.example.aigame.util.Shop
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val shopViewModel: ShopViewModel by activityViewModels()
    private val shopPopUpViewModel: ShopPopUpViewModel by activityViewModels()
    private lateinit var binding: FragmentShopBinding
    private val menuViewModel: MenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        val layoutManager = GridLayoutManager(requireActivity(), 4)
        binding.rvShop.layoutManager = layoutManager
        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvShopMoney.text = it.toString()
            }
        }
        lifecycleScope.launch {
            shopViewModel.stateShop.collect {
                binding.btnShopFurniture.setImageResource(R.drawable.icon_furniture_button_false)
                binding.btnShopChineseZodiac.setImageResource(R.drawable.icon_chinese_zodiac_button_false)
                binding.btnShopChineseArtAndDecor.setImageResource(R.drawable.icon_chinese_art_and_decor_button_false)
                binding.tvShopFurniture.textSize = 14f
                binding.tvShopChineseZodiac.textSize = 14f
                binding.tvShopChineseArtAndDecor.textSize = 14f
                binding.tvShopFurniture.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvShopChineseZodiac.setTextColor(resources.getColor(R.color.dark_yellow))
                binding.tvShopChineseArtAndDecor.setTextColor(resources.getColor(R.color.dark_yellow))
                when (it) {
                    Shop.FURNITURE -> {
                        binding.btnShopFurniture.setImageResource(R.drawable.icon_furniture_button_true)
                        binding.tvShopFurniture.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvShopFurniture.textSize = 22f

                        binding.rvShop.adapter = CardViewAdapter(Constant.furniture, ::onClick)
                    }

                    Shop.CHINESE_ART_AND_DECOR -> {
                        binding.btnShopChineseArtAndDecor.setImageResource(R.drawable.icon_chinese_art_and_decor_button_true)
                        binding.tvShopChineseArtAndDecor.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvShopChineseArtAndDecor.textSize = 22f

                        binding.rvShop.adapter = CardViewAdapter(Constant.chinese_art_and_decor, ::onClick)
                    }

                    Shop.CHINESE_ZODIAC -> {
                        binding.btnShopChineseZodiac.setImageResource(R.drawable.icon_chinese_zodiac_button_true)
                        binding.tvShopChineseZodiac.setTextColor(resources.getColor(R.color.yellow))
                        binding.tvShopChineseZodiac.textSize = 22f

                        binding.rvShop.adapter = CardViewAdapter(Constant.chinese_zodiac, ::onClick)

                    }
                }
            }
        }
        binding.btnShopChineseZodiac.setOnClickListener {
            shopViewModel.loadState(Shop.CHINESE_ZODIAC)
        }
        binding.btnShopChineseArtAndDecor.setOnClickListener {
            shopViewModel.loadState(Shop.CHINESE_ART_AND_DECOR)
        }
        binding.btnShopFurniture.setOnClickListener {
            shopViewModel.loadState(Shop.FURNITURE)
        }
        binding.btnShopBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        return binding.root
    }

    private fun onClick(position: Int, list: List<ShopCardModel>) {
        shopPopUpViewModel.loadState(list[position])
        showPopUpGameOver()
    }

    private fun showPopUpGameOver() {
        val dialog = ShopPopUpFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
    }
}