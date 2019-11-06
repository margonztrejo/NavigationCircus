package com.nav.navigationcircus.graphs

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.nav.navigationcircus.CashOutFragmentEvent
import com.nav.navigationcircus.PickCardFragmentEvent
import com.nav.navigationcircus.PickCardNameEvent
import com.nav.navigationcircus.R
import com.nav.navigationcircus.core.EventResult
import com.nav.navigationcircus.core.NavigationResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.main.NavigationCoordinator

class CashOutGraph(private val coordinator: NavigationCoordinator, private val controller: NavController, private val finishFlowListener: FinishFlowListener): NavigationCoordinator.FlowGraph {

    override fun onResultFragment(fragmentReceiverResult: NavigationResult, result: ScreenEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationResult(eventResult: ScreenEvent) {

    }

    override fun navigateTo(uri: Uri) {
        if(firstCashOut){
            if(hasCardClabe){
                graph?.startDestination = R.id.pickCard
            }else{
                graph?.startDestination = R.id.addCardClabeFragment
            }
        }else{
            graph?.startDestination = R.id.cashOutFragment
        }
        controller.graph = graph!!
    }

    override fun consumeEvent(screenEvent: ScreenEvent) {
        when(screenEvent){
            is PickCardFragmentEvent.OnCardSelected -> {
                controller.navigate(R.id.action_pickCard_to_cashOutFragment)
            }
            is CashOutFragmentEvent -> {
                navigateCashOut(screenEvent)
            }
        }
    }

    override fun onBack(screenEvent: ScreenEvent) {
       when(screenEvent){
            is PickCardNameEvent -> {
                controller.navigateUp()
            }
           else -> {
               controller.navigateUp()
           }
        }
    }

    init {
        setStartGraph()
    }


    private fun navigateCashOut(event: CashOutFragmentEvent){
        when(event){
            is CashOutFragmentEvent.SuccessfulCashOut -> {
                finishFlowListener.onFinishFlow(event)
            }
            CashOutFragmentEvent.FailedCashOut -> {
                finishFlowListener.onFinishFlow(event)
            }
        }
    }

    private fun setStartGraph() {
        graph = controller.navInflater.inflate(R.navigation.cash_out_graph)
    }

    private var graph: NavGraph? = null
    private var firstCashOut: Boolean = true
    private var hasCardClabe: Boolean = true
}