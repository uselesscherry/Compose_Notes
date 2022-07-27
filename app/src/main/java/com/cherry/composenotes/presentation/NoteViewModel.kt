package com.cherry.composenotes.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.composenotes.domain.Note
import com.cherry.composenotes.domain.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoteViewModel(
    private val repo: NoteRepository
) : ViewModel() {


    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    suspend fun getNoteById(id: Int) = repo.getNoteById(id)


    fun insertNote(
        id: Int,
        title: String,
        content: String,
        context: Context,
        navigateBack: () -> Unit
    ) {
        when {
            title.isBlank() -> {
                Toast.makeText(context, "You need to enter note's title", Toast.LENGTH_SHORT).show()
            }
            content.isBlank() -> {
                Toast.makeText(context, "You need to enter note's title", Toast.LENGTH_SHORT).show()
            }
            title.isNotEmpty() && content.isNotEmpty() -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.insert(
                        if (id != -1) Note(id, title, content)
                        else Note(
                            title = title,
                            content = content
                        )
                    )
                }

                navigateBack()
            }
        }

    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(id)
            _state.value = state.value.copy(
                isAlertDialogShown = false, deleteNoteId = null
            )
        }
    }

    fun dismissDeleting() {
        _state.value = state.value.copy(
            isAlertDialogShown = false,
            deleteNoteId = null
        )
    }

    fun showDeleteAlert(id: Int) {
        _state.value = state.value.copy(
            isAlertDialogShown = true,
            deleteNoteId = id
        )
    }

    private fun getNotes() {
        getNotesJob = null
        getNotesJob = repo.getNotes().onEach { notes ->
            withContext(Dispatchers.IO) {

                _state.value = state.value.copy(
                    notes = notes
                )
            }

        }.launchIn(viewModelScope)


    }
}