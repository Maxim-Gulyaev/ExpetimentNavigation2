package com.maxim.expetimentnavigation2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.RootUi
import com.epicwindmill.decomposekmmnavigationsample.components.root.RootComponent
import com.maxim.expetimentnavigation2.ui.theme.ExpetimentNavigation2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = RootComponent(defaultComponentContext())

        enableEdgeToEdge()
        setContent {
            ExpetimentNavigation2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        RootUi(component)
                    }
                }
            }
        }
    }
}
