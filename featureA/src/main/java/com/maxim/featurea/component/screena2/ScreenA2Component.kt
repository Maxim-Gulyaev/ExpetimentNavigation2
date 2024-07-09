package com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena2

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback

class ScreenA2Component (
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : IScreenA2, ComponentContext by componentContext {

    private val backCallback = BackCallback { onFinished() }

    init {
        backHandler.register(backCallback)
    }

    // Used by iOS
    override fun onBackClicked() {
        onFinished()
    }

    // Used by Android
    /*private fun onBackPressed(): Boolean {
        onFinished()

        // Return true to consume the event
        return true
    }*/
}