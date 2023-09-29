package com.example.aigame.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aigame.R
import com.example.aigame.databinding.FragmentCollectionBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.big.BigViewModel
import com.example.aigame.ui.medium.MediumViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.ui.small.SmallViewModel
import com.example.aigame.util.Constant
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch

class CollectionFragment : Fragment() {

    private lateinit var binding: FragmentCollectionBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    private val collectionViewModel: CollectionViewModel by activityViewModels()
    private val smallViewModel: SmallViewModel by activityViewModels()
    private val mediumViewModel: MediumViewModel by activityViewModels()
    private val bigViewModel: BigViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val layoutManager = GridLayoutManager(requireActivity(), 4)
        binding.rvCollection.layoutManager = layoutManager
        binding.rvCollection.adapter = CollectionViewAdapter(Constant.listBuying, ::onClick)
        binding.btnCollectionBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.DIFFICULTY)
        }
        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvCollectionMoney.text = it.toString()
            }
        }
        return binding.root
    }

    private fun onClick(position: Int, list: List<CollectionCardModel>) {

        when (collectionViewModel.stateCollection.value) {
            Navigation.SMALL -> {
                val smallList = smallViewModel.stateSmall.value.toMutableList()
                smallList[collectionViewModel.stateCollectionNumber.value].apply {
                    image = list[position].image
                    background = R.drawable.icon_brown_field
                }
                Constant.listBuying.remove(list[position])
                navigationViewModel.loadState(Navigation.SMALL)
            }

            Navigation.MEDIUM -> {
                val mediumList = mediumViewModel.stateMedium.value.toMutableList()
                mediumList[collectionViewModel.stateCollectionNumber.value].apply {
                    image = list[position].image
                    background = R.drawable.icon_brown_field
                }
                Constant.listBuying.remove(list[position])
                navigationViewModel.loadState(Navigation.MEDIUM)
            }

            Navigation.BIG -> {
                val bigList = bigViewModel.stateBig.value.toMutableList()
                bigList[collectionViewModel.stateCollectionNumber.value].apply {
                    image = list[position].image
                    background = R.drawable.icon_brown_field
                }
                Constant.listBuying.remove(list[position])
                navigationViewModel.loadState(Navigation.BIG)
            }

            else -> {}
        }
    }
}