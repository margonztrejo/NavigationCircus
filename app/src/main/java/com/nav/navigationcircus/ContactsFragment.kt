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

class ContactFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_selected_contact).setOnClickListener {
            navCoordinator?.onScreenEvent(ContactsFragmentEvent.SelectedContact("idUsuarioSeleccionado"))
        }
    }

    override fun getScreen(): ScreenEvent {
        return ContactsFragmentEventName
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}

sealed class ContactsFragmentEvent: ScreenEvent(){
    data class SelectedContact(val user: String): ContactsFragmentEvent()
}

object ContactsFragmentEventName: ScreenEvent()
