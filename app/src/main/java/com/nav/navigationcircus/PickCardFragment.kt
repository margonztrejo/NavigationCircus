package com.nav.navigationcircus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.NavigationResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.main.MainActivity


class PickCard : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_pick_card).setOnClickListener {
            navCoordinator?.onScreenEvent(PickCardFragmentEvent.OnCardSelected("selectedCardToken"))
        }
    }

    override fun getScreen(): ScreenEvent {
        return PickCardNameEvent
    }
}


sealed class PickCardFragmentEvent: ScreenEvent(){
    data class OnCardSelected(val cardToken: String): PickCardFragmentEvent()
}

object PickCardNameEvent: ScreenEvent()