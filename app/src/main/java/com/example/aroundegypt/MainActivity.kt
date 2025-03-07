package com.example.aroundegypt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.aroundegypt.presentaion.navigation.ComposeNavigation
import com.example.aroundegypt.presentaion.theme.AroundEgyptTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            AroundEgyptTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ComposeNavigation()
                }
            }
        }
    }
}