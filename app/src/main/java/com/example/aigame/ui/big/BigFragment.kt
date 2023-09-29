package com.example.aigame.ui.big

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aigame.R
import com.example.aigame.databinding.FragmentBigBinding
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.collection.CollectionViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch

class BigFragment : Fragment() {

    private lateinit var binding: FragmentBigBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    private val bigViewModel: BigViewModel by activityViewModels()
    private val collectionViewModel: CollectionViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBigBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(requireActivity(), 14)
        binding.rvBig.layoutManager = layoutManager

        lifecycleScope.launch {
            bigViewModel.stateBig.collect {
                binding.rvBig.adapter = BigViewAdapter(it, ::onClick)
            }
        }


        binding.btnBigPlus.setOnClickListener {
            val list = bigViewModel.stateBig.value.toMutableList()
            if (list.size < 56) {
                list.add(RoomCardModel(R.drawable.icon_transperent, R.drawable.icon_grey_field))
                bigViewModel.loadState(list)
            }
        }
        binding.btnBigBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.DIFFICULTY)
        }

        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvBigMoney.text = it.toString()
            }
        }
        return binding.root
    }
    private fun onClick(position: Int, list: List<RoomCardModel>) {
        collectionViewModel.loadState(Navigation.BIG)
        collectionViewModel.loadStateNumber(position)
        navigationViewModel.loadState(Navigation.COLLECTION)
    }
}