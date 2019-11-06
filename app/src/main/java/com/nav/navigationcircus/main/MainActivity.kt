package com.nav.navigationcircus.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nav.navigationcircus.R
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.NavigationResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.graphs.MainGraph
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        navigationCoordination = NavigationCoordinator(MainGraph(activity = this, controller = navController!!), navController!!)

        if(DeepLinkCoordinator.isValidIntent(intent))
            navigationCoordination!!.onEvent(intent.data!!)
    }

    fun navigateBackWithResult(result: ScreenEvent) {
        val childFragmentManager = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            val fragmentResult = (childFragmentManager?.fragments?.get(0) as NavigationResult)
            navigationCoordination?.onResultFragment(fragmentResult,result)
            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        navController!!.popBackStack()
    }

    private var navigationCoordination: NavigationCoordinator? = null
    private var navController: NavController? = null

    val navCoordinator
        get() = navigationCoordination

}
