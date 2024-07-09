package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.second

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
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.IScreenB1

@Composable
fun ScreenB1Ui(component: IScreenB1) {
    val model by component.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Screen B1",
                modifier = Modifier.padding(32.dp)
            )
            Text(
                text = "Magic number: ${model.magicNumber}",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                component.navigateToB2Clicked()
            }) {
                Text(
                    text = "Go to Screen B2 (and get a magic number)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}