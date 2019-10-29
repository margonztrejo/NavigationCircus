package com.nav.navigationcircus.paytocontact

import android.net.Uri
import com.nav.navigationcircus.PayToContactFragmentDirections
import com.nav.navigationcircus.R
import com.nav.navigationcircus.core.IUriHandler
import com.nav.navigationcircus.core.ScreenEvent

class PayToContactUriHandler {
    fun getStartDestination(data: PayToContactData): Int {
        return when{
            data.user != null && data.cardToken != null -> {
                R.id.payToContactFragment
            }
            data.user == null -> {
                R.id.contactFragment
            }
            data.cardToken == null -> {
                R.id.pickCard2
            }
            else -> {
                R.id.payToContactFragment
            }
        }
    }

    fun getData(uri: Uri): PayToContactData{
        val user = if(uri.queryParameterNames.contains(PARAM_USER)) uri.getQueryParameter(PARAM_USER) else null
        val cardToken = if(uri.queryParameterNames.contains(PARAM_CARD_TOKEN)) uri.getQueryParameter(PARAM_CARD_TOKEN) else null
        val amount =  (if(uri.queryParameterNames.contains(PARAM_AMOUNT)) uri.getQueryParameter(PARAM_AMOUNT) else null)?.toBigDecimal()

        return PayToContactData(user = user, cardToken = cardToken, amount = amount)
    }

    companion object{
        const val PARAM_AMOUNT = "amount"
        const val PARAM_USER = "id_user"
        const val PARAM_CARD_TOKEN = "token"
    }
}