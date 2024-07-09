package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.first.ScreenAUi
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.second.ScreenBUi
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.third.ScreenCUi
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.IScreenA
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.IScreenB
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.IScreenC
import com.maxim.navigation.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalDecomposeApi
@ExperimentalComposeUiApi
@Composable
fun MainUi(component: IMain) {
    //val model by component.model.subscribeAsState()

    Scaffold(
        topBar = { TopBar(
            title = "Tab",
            component = component
        ) },
        bottomBar = { BottomNavigationBar(
            selectedTab = IMain.Tab.A,
            onClick = component::onTabClick
        ) }
    ) {
        Children(
            stack = component.stack
        ) {
            when (val child = it.instance) {
                is IMain.Child.ScreenA -> ScreenAUi(child.component)
                is IMain.Child.ScreenB -> ScreenBUi(child.component)
                is IMain.Child.ScreenC -> ScreenCUi(child.component)
            }
        }
    }
}

@Composable
fun TopBar(title: String, component: IMain) {
    when (val child = component.stack.value.active.instance) {
        is IMain.Child.ScreenA -> {

            // Subscribe to child (router)state here so that when the router in Screen A updates, it will trigger an update here.
            val routerState by child.component.stack.subscribeAsState()
            when (val subchild = routerState.active.instance) {
                is IScreenA.Child.ScreenA1 -> {
                    TopBarRoot(title)
                }
                is IScreenA.Child.ScreenA2 -> {
                    TopBarBackButton(title) { subchild.component.onBackClicked() }
                }
            }
        }
        is IMain.Child.ScreenB -> {

            val routerState by child.component.stack.subscribeAsState()
            when (val subchild = routerState.active.instance) {
                is IScreenB.Child.ScreenB1 -> {
                    TopBarRoot(title)
                }
                is IScreenB.Child.ScreenB2 -> {
                    TopBarBackButton(title) { subchild.component.onBackClicked() }
                }
            }
        }
        is IMain.Child.ScreenC -> {

            val routerState by child.component.stack.subscribeAsState()
            when (val subchild = routerState.active.instance) {
                is IScreenC.Child.ScreenC1 -> {
                    TopBarRoot(title)
                }
                is IScreenC.Child.ScreenC2 -> {
                    TopBarBackButton(title) { subchild.component.onBackClicked() }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarRoot(title: String) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBackButton(title: String, onBackClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    selectedTab: IMain.Tab,
    onClick: (IMain.Tab) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(painterResource(id = R.drawable.ic_home), contentDescription = "first tab")
            },
            label = { Text(text = "A") },
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.A,
            onClick = {
                onClick(IMain.Tab.A)
            }
        )
        NavigationBarItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_list), contentDescription = "second tab") },
            label = { Text(text = "B") },
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.B,
            onClick = {
                onClick(IMain.Tab.B)
            }
        )
        NavigationBarItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_feedback), contentDescription = "third tab"
            ) },
            label = { Text(text = "C") },
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.C,
            onClick = {
                onClick(IMain.Tab.C)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(IMain.Tab.A, {})
}