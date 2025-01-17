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

class CashOutFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_successful_cash_out).setOnClickListener {
            navCoordinator?.onScreenEvent(CashOutFragmentEvent.SuccessfulCashOut("Ticket de cash out"))
        }
        view.findViewById<AppCompatButton>(R.id.btn_failed_cash_out).setOnClickListener {
            navCoordinator?.onScreenEvent(CashOutFragmentEvent.FailedCashOut)
        }
    }

    override fun getScreen(): ScreenEvent {
        return CashOutNameEvent
    }
}

sealed class CashOutFragmentEvent: ScreenEvent(){
    data class SuccessfulCashOut(val ticketInfo: String): CashOutFragmentEvent()
    object FailedCashOut: CashOutFragmentEvent()
}

object CashOutNameEvent: ScreenEvent()
