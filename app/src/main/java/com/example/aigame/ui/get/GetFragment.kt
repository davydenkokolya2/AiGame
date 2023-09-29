package com.example.aigame.ui.get

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.databinding.FragmentGetBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.util.Constant
import com.example.aigame.util.Navigation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GetFragment : Fragment() {

    private lateinit var binding: FragmentGetBinding
    private val getViewModel: GetViewModel by activityViewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            getViewModel.stateGet.collect {
                if (it != null) {
                    binding.ivGetImage.setImageResource(it.image)
                    binding.tvGetTitle.setText(it.name)
                }
            }
        }
        binding.btnGet.setOnClickListener {
            val item = getViewModel.stateGet.value
            if (item != null) {
                Constant.listBuying.add(CollectionCardModel(item.cost, item.image, item.name, 0))
            }
            navigationViewModel.loadState(Navigation.WHEEL)
        }
        return binding.root
    }
}