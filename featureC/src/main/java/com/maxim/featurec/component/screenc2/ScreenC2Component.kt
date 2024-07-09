package com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc2

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback

class ScreenC2Component (
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : IScreenC2, ComponentContext by componentContext {

    private val backCallback = BackCallback { onFinished() }

    init {
        backHandler.register(backCallback)
    }

    // Used by iOS
    override fun onBackClicked() {
        // Return a result to the previous component
        onFinished()
    }

    // Used by Android
    private fun onBackPressed(): Boolean {
        onBackClicked()

        // Return true to consume the event
        return true
    }
}