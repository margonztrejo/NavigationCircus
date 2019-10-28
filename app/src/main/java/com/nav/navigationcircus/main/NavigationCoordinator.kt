package com.nav.navigationcircus.main

import androidx.navigation.NavController
import com.nav.navigationcircus.core.EventResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.graphs.CashOutGraph
import com.nav.navigationcircus.graphs.FinishFlowListener
import com.nav.navigationcircus.graphs.MainGraph
import com.nav.navigationcircus.graphs.PayToContactGraph


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

    fun onEvent(navigateTo: NavigateTo) {
        when (navigateTo) {
            NavigateTo.PAY_TO_CONTACT -> {
                currentGraph = PayToContactGraph(controller, this)
                currentGraph?.navigateTo()
            }
            NavigateTo.CASH_OUT -> {
                currentGraph = CashOutGraph(controller, this)
                currentGraph?.navigateTo()
            }
        }
    }

    private fun xx() {


        controller.addOnDestinationChangedListener { controller, destination, arguments ->

        }

    }

    fun onScreenEvent(screenEvent: ScreenEvent) {
        currentGraph?.consumeEvent(screenEvent)
    }

    private var previous: String? = null
    private var currentGraph: FlowGraph? = null


    enum class NavigateTo {
        PAY_TO_CONTACT,
        CASH_OUT
    }

    interface FlowGraph {
        fun navigateTo()
        fun consumeEvent(screenEvent: ScreenEvent)
        fun onBack(screenEvent: ScreenEvent)
        fun onNavigationResult(eventResult: ScreenEvent)
    }

}

interface BackStackListener {
    fun onBack(screenEvent: ScreenEvent)

}

