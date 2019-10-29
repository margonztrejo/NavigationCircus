package com.nav.navigationcircus.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.nav.navigationcircus.R
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.graphs.MainGraph
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        navigationCoordination = NavigationCoordinator(MainGraph(activity = this, controller = navController), navController)

        if(DeepLinkCoordinator.isValidIntent(intent))
            navigationCoordination!!.onEvent(intent.data!!)

        /*val childFragmentManager = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager
            .OnBackStackChangedListener {
            //(childFragmentManager?.fragments?.get(0) as NavigationResult).onNavigationResult(result)
            val f  = childFragmentManager?.fragments?.get(0) as BaseFragment
            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
            navigationCoordination?.onBack(f.getScreen())
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)*/

    }

    private var navigationCoordination: NavigationCoordinator? = null

    val navCoordinator
        get() = navigationCoordination

}
