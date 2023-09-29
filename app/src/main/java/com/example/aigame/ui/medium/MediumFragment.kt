package com.example.aigame.ui.medium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aigame.R
import com.example.aigame.databinding.FragmentMediumBinding
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.collection.CollectionViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch

class MediumFragment : Fragment() {

    private lateinit var binding: FragmentMediumBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    private val mediumViewModel: MediumViewModel by activityViewModels()
    private val collectionViewModel: CollectionViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediumBinding.inflate(inflater, container, false)
        val layoutManager = GridLayoutManager(requireActivity(), 10)
        binding.rvMedium.layoutManager = layoutManager

        lifecycleScope.launch {
            mediumViewModel.stateMedium.collect {
                binding.rvMedium.adapter = MediumViewAdapter(it, ::onClick)
            }
        }


        binding.btnMediumPlus.setOnClickListener {
            val list = mediumViewModel.stateMedium.value.toMutableList()
            if (list.size < 30) {
                list.add(RoomCardModel(R.drawable.icon_transperent, R.drawable.icon_grey_field))
                mediumViewModel.loadState(list)
            }
        }
        binding.btnMediumBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.DIFFICULTY)
        }

        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvMediumMoney.text = it.toString()
            }
        }
        return binding.root
    }
    private fun onClick(position: Int, list: List<RoomCardModel>) {
        collectionViewModel.loadState(Navigation.MEDIUM)
        collectionViewModel.loadStateNumber(position)
        navigationViewModel.loadState(Navigation.COLLECTION)
    }
}