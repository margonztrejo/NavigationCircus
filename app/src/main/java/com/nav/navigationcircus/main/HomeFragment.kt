package com.nav.navigationcircus.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.net.toUri

import com.nav.navigationcircus.R
import com.nav.navigationcircus.core.BaseFragment
import com.nav.navigationcircus.core.Features

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_pay_to_contact).setOnClickListener {
            val uri = "swapme.mx://".toUri()
            navCoordinator?.onEvent(uri)
        }
        view.findViewById<AppCompatButton>(R.id.btn_cash_out).setOnClickListener {
            val uri = "swapme.mx://".toUri()
            navCoordinator?.onEvent(uri)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
