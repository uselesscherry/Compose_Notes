package com.cherry.composenotes.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cherry.composenotes.presentation.NoteViewModel
import com.cherry.composenotes.presentation.screens.AddEditNoteScreen
import com.cherry.composenotes.presentation.screens.NoteListScreen

@Composable
fun MainNavHost(navController: NavHostController, viewModel: NoteViewModel) {
    NavHost(navController = navController, startDestination = Screen.NoteList.route) {
        composable(route = Screen.NoteList.route) {
            NoteListScreen(navController, viewModel)
        }
        composable(
            route = Screen.AddEditNoteScreen.route,
            arguments = listOf(
                navArgument(NOTESCREEN_ARG) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditNoteScreen(it.arguments?.getInt(NOTESCREEN_ARG) ?: -1, navController, viewModel)
        }
    }
}