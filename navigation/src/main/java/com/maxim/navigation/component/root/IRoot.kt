package com.epicwindmill.decomposekmmnavigationsample.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain

interface IRoot {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Main(val component: IMain) : Child()
    }
}