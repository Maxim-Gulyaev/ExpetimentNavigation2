package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.third

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1.IScreenC1

@Composable
fun ScreenC1Ui(component: IScreenC1) {
    val model by component.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Screen C1",
                modifier = Modifier.padding(32.dp)
            )
            Text(
                text = "Magic number: ${model.magicNumber}",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                component.navigateToC2Clicked()
            }) {
                Text(
                    text = "Go to Screen C2 (and get a magic number)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}