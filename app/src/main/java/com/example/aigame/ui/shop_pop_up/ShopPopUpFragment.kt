package com.example.aigame.ui.shop_pop_up

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.R
import com.example.aigame.databinding.FragmentShopPopUpBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.ui.ShopPopUp_pop_up.ShopPopUpViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Constant
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShopPopUpFragment : DialogFragment() {

    private val shopPopUpViewModel: ShopPopUpViewModel by activityViewModels()
    private lateinit var binding: FragmentShopPopUpBinding
    private val menuViewModel: MenuViewModel by activityViewModels()
    private var count = 1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopPopUpBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            shopPopUpViewModel.stateShopPopUp.collect {
                binding.tvShopPopUpCost.text = it.cost.toString()
                binding.tvShopPopUpName.setText(it.name)
                binding.ivShowPopUpImage.setImageResource(it.image)
            }
        }

        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvShopPopUpMoney.text = it.toString()
            }
        }
        binding.btnShopPopUpClose.setOnClickListener {
            onStop()
        }
        binding.btnShopPopUpPlus.setOnClickListener {
            if (((count + 1) * shopPopUpViewModel.stateShopPopUp.value.cost) <= menuViewModel.stateMenu.value) {
                count++
                binding.tvShopPopUpCount.text = count.toString()
            }
        }
        binding.btnShopPopUpMinus.setOnClickListener {
            if (count - 1 > 0) {
                count--
                binding.tvShopPopUpCount.text = count.toString()
            }
        }
        binding.btnShopPopUpBuy.setOnClickListener {
            if (menuViewModel.stateMenu.value > 0) {
                val shopCardModel = shopPopUpViewModel.stateShopPopUp.value
                for (i in 1..count) {
                    Constant.listBuying.add(
                        CollectionCardModel(
                            shopCardModel.cost,
                            shopCardModel.image,
                            shopCardModel.name,
                            count
                        )
                    )
                }
                menuViewModel.loadState(menuViewModel.stateMenu.value - (count * shopCardModel.cost))
            }
        }
        return binding.root
    }

}