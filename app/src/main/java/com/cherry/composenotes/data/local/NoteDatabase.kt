package com.cherry.composenotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cherry.composenotes.domain.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        const val DB_NAME = "note.db"
    }
}