package com.nav.navigationcircus.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import com.nav.navigationcircus.*
import com.nav.navigationcircus.core.EventResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.main.NavigationCoordinator

class PayToContactGraph(private val controller: NavController, private val finishFlowListener: FinishFlowListener) :
    NavigationCoordinator.FlowGraph {
    override fun onNavigationResult(eventResult: ScreenEvent) {

    }


    override fun consumeEvent(screenEvent: ScreenEvent) {
        when (screenEvent) {
            is PermissionsFragmentEvent -> {
                navigatePermissionsEvent(screenEvent)
            }
            is PayToContactFragmentEvent -> {
                navigatePayToContact(screenEvent)
            }
            is ContactsFragmentEvent -> {
                navigateContact(screenEvent)
            }
            is PickCardFragmentEvent -> {
                navigatePickCard(screenEvent)
            }
        }
    }

    private fun navigatePermissionsEvent(event: PermissionsFragmentEvent) {
        when (event) {
            is PermissionsFragmentEvent.AcceptedPermissions -> {
                controller.navigate(R.id.action_permissionsFragment_to_contactFragment)
            }
            is PermissionsFragmentEvent.DeclinedPermissions -> TODO()
        }
    }

    private fun navigatePayToContact(event: PayToContactFragmentEvent) {
        when(event){
            is PayToContactFragmentEvent.SuccessfulPayment -> {
                finishFlowListener.onFinishFlow(event)
            }
            is PayToContactFragmentEvent.FailedPayment -> {
                finishFlowListener.onFinishFlow(event)
            }
            is PayToContactFragmentEvent.PickCard -> {
                controller.navigate(R.id.action_payToContactFragment_to_pickCard2)
            }
        }
    }

    private fun navigateContact(event: ContactsFragmentEvent){
        when(event){
            ContactsFragmentEvent.SelectedContact -> {
                controller.navigate(R.id.action_contactFragment_to_pickCard2)
            }
        }
    }

    private fun navigatePickCard(event: PickCardFragmentEvent){
        when(event){
            PickCardFragmentEvent.OnCardSelected -> {
                //controller.navigate(R.id.action_pickCard2_to_payToContactFragment, null, NavOptions.Builder().setPopUpTo(R.id.pickCard2, true).build())
                controller.navigate(R.id.action_pickCard2_to_payToContactFragment)
            }
        }
    }

    override fun onBack(screenEvent: ScreenEvent) {
        when(screenEvent){
            is ContactsFragmentEventName -> {
                finishFlowListener.onFinishFlow(screenEvent)
            }
            else ->{
                controller.navigateUp()
            }
        }
    }

    init {
        setStartGraph()
    }

    private fun setStartGraph() {
        graph = controller.navInflater.inflate(R.navigation.pay_to_contact_graph)
    }

    override fun navigateTo() {
        if (hasContactPermission) {
            graph?.startDestination = R.id.contactFragment
        } else {
            graph?.startDestination = R.id.permissionsFragment
        }
        controller.graph = graph!!
    }



    private var graph: NavGraph? = null

    private var hasContactPermission: Boolean = false
}