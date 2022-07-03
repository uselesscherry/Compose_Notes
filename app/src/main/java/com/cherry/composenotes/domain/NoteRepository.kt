package com.cherry.composenotes.domain

import kotlinx.coroutines.flow.Flow


interface NoteRepository {

     fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note

    suspend fun insert(note: Note)

    suspend fun delete(id: Int)
}