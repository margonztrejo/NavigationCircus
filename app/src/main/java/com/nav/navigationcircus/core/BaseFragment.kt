package com.nav.navigationcircus.core

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.nav.navigationcircus.main.MainActivity
import com.nav.navigationcircus.main.NavigationCoordinator

open class BaseFragment: Fragment(), NavigationResult {
    override fun onNavigationResult(result: ScreenEvent) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navCoordinator = (activity as MainActivity).navCoordinator
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            navCoordinator?.onBack(getScreen())
        }
    }

    open fun getScreen():ScreenEvent{
        return  ScreenEvent()
    }
    protected var navCoordinator: NavigationCoordinator? = null
}