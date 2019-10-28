package com.nav.navigationcircus

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.ScreenEvent


class PayToContactFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_to_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_successful_payment).setOnClickListener {
            navCoordinator?.onScreenEvent(PayToContactFragmentEvent.SuccessfulPayment("Ticket de pago"))
        }
        view.findViewById<AppCompatButton>(R.id.btn_failed_payment).setOnClickListener {
            navCoordinator?.onScreenEvent(PayToContactFragmentEvent.FailedPayment)
        }
        view.findViewById<AppCompatButton>(R.id.btn_pick_card).setOnClickListener {
            navCoordinator?.onScreenEvent(PayToContactFragmentEvent.PickCard)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PayToContactFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getScreen(): ScreenEvent {
        return PayToContactName
    }
}

sealed class PayToContactFragmentEvent: ScreenEvent(){
    data class SuccessfulPayment(val ticketInfo: String): PayToContactFragmentEvent()
    object FailedPayment: PayToContactFragmentEvent()
    object PickCard: PayToContactFragmentEvent()
}

object PayToContactName: ScreenEvent()
