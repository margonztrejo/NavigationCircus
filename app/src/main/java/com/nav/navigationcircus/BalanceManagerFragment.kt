package com.nav.navigationcircus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController


class BalanceManagerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_balance_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.btn_without_cards).setOnClickListener {
            val action = BalanceManagerFragmentDirections.actionBalanceManagerFragmentToAddCardClabeFragment()
            findNavController().navigate(action)
        }
        view.findViewById<AppCompatButton>(R.id.btn_with_cards).setOnClickListener {
            val action = BalanceManagerFragmentDirections.actionBalanceManagerFragmentToPickCard()
            findNavController().navigate(action)
        }
    }
}
