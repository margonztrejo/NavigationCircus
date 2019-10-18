package com.nav.navigationcircus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class FragmentB : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //val txId = args.transactionId
        //Toast.makeText(requireContext(), "arg = $txId", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }


   // private val args: FragmentBArgs by navArgs()


    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentB().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
