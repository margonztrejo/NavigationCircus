package com.nav.navigationcircus.core

import android.net.Uri

interface IUriHandler {
    fun getStartDestination(uri: Uri): Int
}