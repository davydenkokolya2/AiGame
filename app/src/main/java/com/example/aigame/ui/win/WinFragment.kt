package com.example.aigame.ui.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aigame.databinding.FragmentWinBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.domain.ShopCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.get.GetViewModel
import com.example.aigame.util.Constant
import com.example.aigame.util.Navigation
import java.util.Random

class WinFragment : Fragment() {

    private lateinit var binding: FragmentWinBinding
    private val getViewModel: GetViewModel by activityViewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWinBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener {
            var item: ShopCardModel = ShopCardModel(0, 0, 0)
            when (Random().nextInt(3)) {
                0 -> item = Constant.furniture[Random().nextInt(Constant.furniture.size)]
                1 -> item = Constant.chinese_art_and_decor[Random().nextInt(Constant.chinese_art_and_decor.size)]
                2 -> item = Constant.chinese_zodiac[Random().nextInt(Constant.chinese_zodiac.size)]
            }
            getViewModel.loadState(CollectionCardModel(item.cost, item.image, item.name, 1))
            navigationViewModel.loadState(Navigation.GET)
        }
        return binding.root
    }

}