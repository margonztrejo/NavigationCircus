package com.nav.navigationcircus.paytocontact

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import com.nav.navigationcircus.*
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.graphs.FinishFlowListener
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

    init {
        initData()
    }

    private fun initData(){
        uriHandler = PayToContactUriHandler()
    }

    private fun navigatePermissionsEvent(event: PermissionsFragmentEvent) {
        when (event) {
            is PermissionsFragmentEvent.AcceptedPermissions -> {
                setPrevious(R.id.contactFragment)
                payToContactData.hasContactPermission = true
                controller.navigate(R.id.action_permissionsFragment_to_contactFragment)
            }
            is PermissionsFragmentEvent.DeclinedPermissions -> {
                payToContactData.hasContactPermission = false
            }
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
                setPrevious(R.id.pickCard2)
                controller.navigate(R.id.action_payToContactFragment_to_pickCard2)
            }
        }
    }

    private fun navigateContact(event: ContactsFragmentEvent){
        when(event){
            is ContactsFragmentEvent.SelectedContact -> {
                payToContactData.user = event.user
                if(payToContactData.cardToken != null){
                    setPrevious(R.id.payToContactFragment)
                    val action = ContactFragmentDirections.actionContactFragmentToPayToContactFragment(payToContactData)
                    controller.navigate(action)
                }else{
                    setPrevious(R.id.pickCard2)
                    controller.navigate(R.id.action_contactFragment_to_pickCard2)
                }
            }
        }
    }

    private fun navigatePickCard(event: PickCardFragmentEvent){
        when(event){
            is PickCardFragmentEvent.OnCardSelected -> {
                if(previous == R.id.pickCard2){
                    payToContactData.cardToken = event.cardToken
                    setPrevious(R.id.payToContactFragment)
                    controller.popBackStack()
                    val action = PickCardDirections.actionPickCard2ToPayToContactFragment(payToContactData)
                    controller.navigate(action)
                }else{
                    setPrevious(R.id.payToContactFragment)
                    payToContactData.cardToken = event.cardToken
                    val action = PickCardDirections.actionPickCard2ToPayToContactFragment(payToContactData)
                    controller.navigate(action)
                }
            }
        }
    }

    override fun onBack(screenEvent: ScreenEvent) {
        when(screenEvent){
            is ContactsFragmentEventName -> {
                finishFlowListener.onFinishFlow(screenEvent)
            }
            else ->{
                if(!controller.navigateUp()){
                    finishFlowListener.onFinishFlow(EventBack)
                }
            }
        }
    }

    init {
        setStartGraph()
    }

    private fun setStartGraph() {
        graph = controller.navInflater.inflate(R.navigation.pay_to_contact_graph)
    }

    override fun navigateTo(uri: Uri) {
        payToContactData = uriHandler!!.getData(uri)
        payToContactData.hasContactPermission = hasContactPermission
        val bundle = Bundle()
        bundle.putParcelable("data", payToContactData)
        graph?.startDestination = uriHandler!!.getStartDestination(payToContactData)
        controller.setGraph(graph!!, bundle)
    }
    private fun setPrevious(id: Int){
        previous = id
    }

    private var payToContactData = PayToContactData()

    private var graph: NavGraph? = null

    private var hasContactPermission: Boolean = false

    private var uriHandler: PayToContactUriHandler? = null

    private var previous: Int? = null

    object EventBack: ScreenEvent()

}