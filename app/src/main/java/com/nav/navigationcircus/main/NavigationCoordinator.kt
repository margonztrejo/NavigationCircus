package com.nav.navigationcircus.main

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import com.nav.navigationcircus.core.Features
import com.nav.navigationcircus.core.NavigationResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.graphs.CashOutGraph
import com.nav.navigationcircus.graphs.FinishFlowListener
import com.nav.navigationcircus.graphs.MainGraph
import com.nav.navigationcircus.paytocontact.PayToContactGraph


class NavigationCoordinator(private val mainGraph: MainGraph, private val controller: NavController) : BackStackListener, FinishFlowListener {
    override fun onFinishFlow(eventResult: ScreenEvent) {
        when(currentGraph){
            is PayToContactGraph -> {
                currentGraph = mainGraph
                currentGraph?.onNavigationResult(eventResult)
            }
            is CashOutGraph -> {
                currentGraph = mainGraph
                currentGraph?.onNavigationResult(eventResult)
            }
        }
    }

    override fun onBack(screenEvent: ScreenEvent) {
        currentGraph?.onBack(screenEvent)
    }


    init {
        xx()
    }

    fun onEvent(uri: Uri) {
        when (DeepLinkCoordinator.getFeatureFromUri(uri)) {
            Features.UNHANDLER -> {

            }
            Features.PAY_TO_CONTACT -> {
                currentGraph =
                    PayToContactGraph(this, controller, this)
            }
            Features.CASH_OUT -> {
                currentGraph = CashOutGraph(this, controller, this)
            }
        }

        currentGraph?.navigateTo(uri)
    }

    private fun xx() {

        currentGraph = mainGraph
        controller.addOnDestinationChangedListener { controller, destination, arguments ->

        }

    }

    fun onResultFragment(fragmentReceiverResult:NavigationResult, result:ScreenEvent){
        currentGraph?.onResultFragment(fragmentReceiverResult,result)
    }

    fun onScreenEvent(screenEvent: ScreenEvent) {
        currentGraph?.consumeEvent(screenEvent)
    }

    fun navigateBackWithResult(result: ScreenEvent){
        mainGraph.activity.navigateBackWithResult(result)
    }

    private var currentGraph: FlowGraph? = null

    interface FlowGraph {
        fun navigateTo(uri: Uri)
        fun consumeEvent(screenEvent: ScreenEvent)
        fun onBack(screenEvent: ScreenEvent)
        fun onNavigationResult(eventResult: ScreenEvent)
        fun onResultFragment(fragmentReceiverResult: NavigationResult,result: ScreenEvent)
    }

}

interface BackStackListener {
    fun onBack(screenEvent: ScreenEvent)

}

