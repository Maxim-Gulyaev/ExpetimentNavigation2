package com.epicwindmill.decomposekmmnavigationsample.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.IScreenA
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.ScreenAComponent
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.IScreenB
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.ScreenBComponent
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.IScreenC
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.ScreenCComponent

class MainComponent(
    componentContext: ComponentContext, /*override val model: Value<IMain.Model>*/
) : IMain, ComponentContext by componentContext {

    /*private val router =
        router<Config, IMain.Child>(
            initialConfiguration = Config.ScreenA,
            handleBackButton = true,
            childFactory = ::createChild
        )*/

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, IMain.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    /*override val model: Value<IMain.Model> =
        router.state.map { state ->
            IMain.Model(
                selectedTab = state.activeChild.configuration.toTab()
            )
        }*/

    private fun createChild(config: Config, componentContext: ComponentContext): IMain.Child =
        when (config) {
            is Config.ScreenA -> IMain.Child.ScreenA(screenA(componentContext))
            is Config.ScreenB -> IMain.Child.ScreenB(screenB(componentContext))
            is Config.ScreenC -> IMain.Child.ScreenC(screenC(componentContext))
        }

    private fun screenA(componentContext: ComponentContext): IScreenA =
        ScreenAComponent(componentContext)

    private fun screenB(componentContext: ComponentContext): IScreenB =
        ScreenBComponent(componentContext)

    private fun screenC(componentContext: ComponentContext): IScreenC =
        ScreenCComponent(componentContext)

    override fun onTabClick(tab: IMain.Tab): Unit =
        when (tab) {
            IMain.Tab.A -> navigation.push(Config.ScreenA)
            IMain.Tab.B -> navigation.push(Config.ScreenB)
            IMain.Tab.C -> navigation.push(Config.ScreenC)
        }

    private fun Config.toTab(): IMain.Tab =
        when (this) {
            is Config.ScreenA -> IMain.Tab.A
            is Config.ScreenB -> IMain.Tab.B
            is Config.ScreenC -> IMain.Tab.C
        }

    private sealed class Config : Parcelable {
        @Parcelize
        data object ScreenA : Config()

        @Parcelize
        data object ScreenB : Config()

        @Parcelize
        data object ScreenC : Config()
    }
}