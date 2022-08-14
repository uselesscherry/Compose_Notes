package com.cherry.composenotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cherry.composenotes.presentation.screens.navigation.MainNavHost
import com.cherry.composenotes.ui.theme.ComposeNotesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: NoteViewModel by viewModel()
        setContent {
            val navController = rememberNavController()
            ComposeNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}
