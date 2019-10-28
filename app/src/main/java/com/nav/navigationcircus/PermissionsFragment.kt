package com.nav.navigationcircus

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.EventResult
import com.nav.navigationcircus.core.ScreenEvent
import com.nav.navigationcircus.main.NavigationCoordinator

class PermissionsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permissions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_permissions).setOnClickListener {
            navCoordinator?.onScreenEvent(PermissionsFragmentEvent.AcceptedPermissions)
        }
    }

    override fun getScreen(): ScreenEvent {
        return PermissionsFragmentEventName.PermissionsFragmentEventName
    }
}


sealed class PermissionsFragmentEvent : ScreenEvent() {
    object AcceptedPermissions : PermissionsFragmentEvent()
    object DeclinedPermissions : PermissionsFragmentEvent()
}

sealed class PermissionsFragmentEventName : ScreenEvent() {
    object PermissionsFragmentEventName : ScreenEvent()
}