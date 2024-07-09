package com.epicwindmill.decomposekmmnavigationsample.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain
import com.epicwindmill.decomposekmmnavigationsample.components.main.MainComponent

class RootComponent(
    componentContext: ComponentContext
) : IRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    /*private val router =
        router<Config, IRoot.Child>(
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )*/

    override val stack: Value<ChildStack<*, IRoot.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Main,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, componentContext: ComponentContext): IRoot.Child =
        when (config) {
            is Config.Main -> IRoot.Child.Main(main(componentContext))
        }

    private fun main(componentContext: ComponentContext): IMain =
        MainComponent(componentContext)

    private sealed class Config : Parcelable {
        @Parcelize
        object Main : Config()
    }
}