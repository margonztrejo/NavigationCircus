package com.nav.navigationcircus

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController

class AddCardClabeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_card_clabe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_add_card).setOnClickListener {
            val action = AddCardClabeFragmentDirections.actionAddCardClabeFragmentToAddCardFragment()
            findNavController().navigate(action)
        }

        view.findViewById<AppCompatButton>(R.id.btn_add_clabe).setOnClickListener {
            val action = AddCardClabeFragmentDirections.actionAddCardClabeFragmentToAddClabeFragment()
            findNavController().navigate(action)
        }
    }
}
