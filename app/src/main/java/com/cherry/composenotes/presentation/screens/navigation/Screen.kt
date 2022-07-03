package com.cherry.composenotes.presentation.screens.navigation

const val NOTESCREEN_ARG = "id"

sealed class Screen(val route: String){
    object NoteList:Screen(route = "note_list")
    object AddEditNoteScreen:Screen(route = "add_edit_note_screen?$NOTESCREEN_ARG={$NOTESCREEN_ARG}"){
        fun withArgument(id:Int):String = route.replaceAfter('=',id.toString())
    }
}
