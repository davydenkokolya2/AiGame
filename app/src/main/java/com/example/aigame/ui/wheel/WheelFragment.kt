package com.example.aigame.ui.wheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.R
import com.example.aigame.databinding.FragmentWheelBinding
import com.example.aigame.ui.NavigationViewModel
import com.example.aigame.ui.TimerViewModel
import com.example.aigame.ui.Wheel
import com.example.aigame.ui.menu.MenuViewModel
import com.example.aigame.util.Navigation
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class WheelFragment : Fragment() {


    private lateinit var binding: FragmentWheelBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val menuViewModel: MenuViewModel by activityViewModels()
    private val wheelViewModel: WheelViewModel by activityViewModels()
    private val timerViewModel: TimerViewModel by activityViewModels()
    private var degrees = 0F
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            menuViewModel.stateMenu.collect {
                binding.tvWheelMoney.text = it.toString()
            }
        }
        lifecycleScope.launch {
            timerViewModel.stateTimer.collect {
                binding.tvWheelTime.text = it
            }
        }
        lifecycleScope.launch {
            wheelViewModel.stateWheel.collect {
                when (it) {
                    Wheel.TIMER -> {
                        binding.ivWheel.setImageResource(R.drawable.icon_transparent_wheel)
                        binding.ivWheelWheel.visibility = View.INVISIBLE
                        binding.tvWheelTime.visibility = View.VISIBLE
                        binding.btnWheelSpin.visibility = View.INVISIBLE
                    }

                    Wheel.SPIN -> {
                        binding.ivWheel.setImageResource(R.drawable.icon_round_wheel)
                        binding.ivWheelWheel.visibility = View.VISIBLE
                        binding.btnWheelSpin.visibility = View.VISIBLE
                        binding.tvWheelTime.visibility = View.INVISIBLE
                    }
                }
            }
        }
        binding.btnWheelBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        binding.btnWheelSpin.setOnClickListener {
            startWheel()
        }
        return binding.root
    }

    private fun startWheel() {
        val randomItem = (900..1260).random()
        degrees += randomItem
        val resultRotate = RotateAnimation(
            0f,
            randomItem.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        resultRotate.duration = 2000
        resultRotate.fillAfter = true
        resultRotate.interpolator = DecelerateInterpolator()
        resultRotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.btnWheelSpin.isClickable = false
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.btnWheelSpin.isClickable = true
                wheelViewModel.loadState(Wheel.TIMER)
                navigationViewModel.loadState(Navigation.WIN)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.ivWheelWheel.startAnimation(resultRotate)
    }


}