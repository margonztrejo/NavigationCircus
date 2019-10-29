package com.nav.navigationcircus.paytocontact

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.math.BigDecimal

data class PayToContactData(var user: String? = null, var cardToken: String? = null, var amount: BigDecimal? = null, var hasContactPermission: Boolean = false): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()?.toBigDecimal(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user)
        parcel.writeString(cardToken)
        parcel.writeByte(if (hasContactPermission) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PayToContactData> {
        override fun createFromParcel(parcel: Parcel): PayToContactData {
            return PayToContactData(parcel)
        }

        override fun newArray(size: Int): Array<PayToContactData?> {
            return arrayOfNulls(size)
        }
    }
}