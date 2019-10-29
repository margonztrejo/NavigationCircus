package com.nav.navigationcircus.main

import android.content.Intent
import android.net.Uri
import com.nav.navigationcircus.core.Features
import java.util.*

class DeepLinkCoordinator {

    companion object{
        @JvmStatic
        fun isValidIntent(intent: Intent?): Boolean{
            if(intent == null)
                return false

            if(intent.data == null)
                return false

            val uri = intent.data!!

            if(uri.scheme == null)
                return false

            if(uri.scheme!!.toLowerCase() != PATH_SWAP)
                return false

            return true
        }

        @JvmStatic
        fun getFeatureFromUri(uri: Uri): Features{
            return when(uri.host){
                HOST_PAY_TO_CONTACT -> {
                    Features.PAY_TO_CONTACT
                }
                HOST_CASH_OUT -> {
                    Features.CASH_OUT
                }
                else -> {
                    Features.UNHANDLER
                }
            }
        }


        private const val PATH_SWAP = "swapme.mx"

        private const val HOST_PAY_TO_CONTACT = "payToContact"
        private const val HOST_CASH_OUT = "cashOut"
    }
}