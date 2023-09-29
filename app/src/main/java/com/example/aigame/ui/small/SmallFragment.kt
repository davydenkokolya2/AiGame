package com.example.aigame.ui.small

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aigame.R
import com.example.aigame.databinding.FragmentSmallBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.collection.CollectionViewAdapter
import com.example.aigame.ui.collection.CollectionViewModel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Constant
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch

class SmallFragment : Fragment() {

    private lateinit var binding: FragmentSmallBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    private val smallViewModel: SmallViewModel by activityViewModels()
    private val collectionViewModel: CollectionViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmallBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(requireActivity(), 7)
        binding.rvSmall.layoutManager = layoutManager

        lifecycleScope.launch {
            smallViewModel.stateSmall.collect {
                binding.rvSmall.adapter = SmallViewAdapter(it, ::onClick)
            }
        }


        binding.btnSmallPlus.setOnClickListener {
            val list = smallViewModel.stateSmall.value.toMutableList()
            if (list.size < 14) {
                list.add(RoomCardModel(R.drawable.icon_transperent, R.drawable.icon_grey_field))
                smallViewModel.loadState(list)
            }
        }
        binding.btnSmallBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.DIFFICULTY)
        }

        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvSmallMoney.text = it.toString()
            }
        }
        return binding.root
    }

    private fun onClick(position: Int, list: List<RoomCardModel>) {
        collectionViewModel.loadState(Navigation.SMALL)
        collectionViewModel.loadStateNumber(position)
        navigationViewModel.loadState(Navigation.COLLECTION)
    }
}