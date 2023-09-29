package com.example.aigame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.aigame.R
import com.example.aigame.ui.big.BigFragment
import com.example.aigame.ui.collection.CollectionFragment
import com.example.aigame.ui.collection.CollectionViewAdapter
import com.example.aigame.ui.difficulty.DifficultyFragment
import com.example.aigame.ui.get.GetFragment
import com.example.aigame.ui.info.InfoFragment
import com.example.aigame.ui.medium.MediumFragment
import com.example.aigame.ui.menu.MenuFragment
import com.example.aigame.ui.shop.ShopFragment
import com.example.aigame.ui.small.SmallFragment
import com.example.aigame.ui.wheel.WheelFragment
import com.example.aigame.ui.wheel.WheelViewModel
import com.example.aigame.ui.win.WinFragment
import com.example.aigame.util.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()
    private val wheelViewModel: WheelViewModel by viewModels()
    private val timerViewModel: TimerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            navigationViewModel.stateNavigation.collect {
                when(it) {
                    Navigation.MENU -> replaceFragment(MenuFragment())
                    Navigation.DIFFICULTY -> replaceFragment(DifficultyFragment())
                    Navigation.INFO -> replaceFragment(InfoFragment())
                    Navigation.SHOP -> replaceFragment(ShopFragment())
                    Navigation.WHEEL -> replaceFragment(WheelFragment())
                    Navigation.WIN -> replaceFragment(WinFragment())
                    Navigation.GET -> replaceFragment(GetFragment())
                    Navigation.SMALL -> replaceFragment(SmallFragment())
                    Navigation.MEDIUM -> replaceFragment(MediumFragment())
                    Navigation.BIG -> replaceFragment(BigFragment())
                    Navigation.COLLECTION -> replaceFragment(CollectionFragment())
                }
            }
        }

        lifecycleScope.launch {
            wheelViewModel.stateWheel.collect {
                when(it) {
                    Wheel.TIMER -> runTimer()
                    Wheel.SPIN -> ""
                }
            }
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    private suspend fun runTimer() {
        var i = 60
        while (i > 0) {
            delay(1000)
            var text = ""
            text = if (i / 60 in 0..9)
                "0${i / 60}"
            else
                "${i / 60}"
            text += if (i % 60 in 0..9)
                ":0${i % 60}"
            else
                ":${i % 60}"
            timerViewModel.loadState(text)
            i--
        }
        wheelViewModel.loadState(Wheel.SPIN)
    }
}