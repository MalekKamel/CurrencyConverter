package com.currencyconverter.app

import android.os.Bundle
import com.sha.kamel.navigator.FragmentNavigator
import currencyconverter.common.presentation.navigation.Fragments
import currencyconverter.common.presentation.navigation.fragmentFrom
import currencyconverter.common.presentation.ui.activity.BaseActivity

class MainActivity : BaseActivity() {

    override var layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            FragmentNavigator(this).replace(fragmentFrom(Fragments.Splash), false)
    }
}
