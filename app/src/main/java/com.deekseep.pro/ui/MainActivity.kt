package com.deekseep.pro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.deekseep.pro.config.Preferences
import com.deekseep.pro.ui.screens.ChatScreen
import com.deekseep.pro.ui.screens.HomeScreen
import com.deekseep.pro.ui.screens.SettingsScreen
import com.deekseep.pro.ui.theme.DeekseepProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val ctx = LocalContext.current
            LaunchedEffect(Unit) { Preferences.init(ctx) }
            DeekseepProTheme { MainApp() }
        }
    }
}

@Composable
fun MainApp() {
    var page by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = page == 0, onClick = { page = 0 },
                    icon = { Icon(Icons.Default.Home, "主页") },
                    label = { Text("主页") }
                )
                NavigationBarItem(
                    selected = page == 1, onClick = { page = 1 },
                    icon = { Icon(Icons.Default.Chat, "对话") },
                    label = { Text("对话") }
                )
                NavigationBarItem(
                    selected = page == 2, onClick = { page = 2 },
                    icon = { Icon(Icons.Default.Settings, "设置") },
                    label = { Text("设置") }
                )
            }
        }
    ) { pad ->
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                (fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 6 }) togetherWith
                (fadeOut(tween(300)) + slideOutHorizontally(tween(300)) { -it / 6 })
            },
            label = "page",
            modifier = Modifier.padding(pad)
        ) { p ->
            when (p) {
                0 -> HomeScreen()
                1 -> ChatScreen()
                2 -> SettingsScreen()
            }
        }
    }
}
