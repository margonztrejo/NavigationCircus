package com.nav.navigationcircus.graphs

import com.nav.navigationcircus.core.ScreenEvent

interface FinishFlowListener {
    fun onFinishFlow(eventResult: ScreenEvent)
}