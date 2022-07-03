package com.cherry.composenotes.presentation

import com.cherry.composenotes.domain.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val isAlertDialogShown: Boolean = false,
    val deleteNoteId:Int? = null
)
