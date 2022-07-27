package com.cherry.composenotes.data.local

import com.cherry.composenotes.domain.Note
import com.cherry.composenotes.domain.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl (
    db: NoteDatabase
) : NoteRepository {

    private val noteDao = db.noteDao

    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun getNoteById(id: Int): Note = noteDao.getNoteById(id)

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun delete(id: Int) {
        noteDao.deleteNoteById(id)
    }

}