package com.nav.navigationcircus.graphs

import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.nav.navigationcircus.CashOutFragmentEvent
import com.nav.navigationcircus.PayToContactFragmentEvent
import com.nav.navigationcircus.R
import com.nav.navigationcircus.TicketFragment
import com.nav.navigationcircus.core.EventResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.main.MainActivity
import com.nav.navigationcircus.main.NavigationCoordinator

class MainGraph(private val activity: MainActivity, private val controller: NavController): NavigationCoordinator.FlowGraph {
    override fun onNavigationResult(eventResult: ScreenEvent) {
        val uri = "".toUri()
        navigateTo(uri)
        when(eventResult){
            is PayToContactFragmentEvent.SuccessfulPayment -> {
                showTicket(eventResult.ticketInfo)
            }
            is PayToContactFragmentEvent.FailedPayment -> {
                Toast.makeText(activity, "Fallo el pago", Toast.LENGTH_LONG).show()
            }
            is CashOutFragmentEvent.SuccessfulCashOut -> {
                showTicket(eventResult.ticketInfo)
            }
        }
    }

    init {
        setStartGraph()
    }

    private fun showTicket(info: String){
        val ticket = TicketFragment.newInstance(info)
        ticket.show(activity.supportFragmentManager, "TAG")
    }

    private fun setStartGraph() {
        graph = controller.navInflater.inflate(R.navigation.navigation_example)
    }

    override fun navigateTo(uri: Uri) {
        controller.graph = graph!!
    }

    override fun consumeEvent(screenEvent: ScreenEvent) {

    }

    override fun onBack(screenEvent: ScreenEvent) {
        activity.finish()
    }

    private var graph: NavGraph? = null
}

enum class ResultsForMainGraph{
    SHOW_TICKET,
    SHOW_HAPPY_MOMENTS
}