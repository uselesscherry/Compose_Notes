package com.cherry.composenotes.data.local

import androidx.room.*
import com.cherry.composenotes.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
     fun getNotes(): Flow<List<Note>>


    @Query("SELECT * FROM note WHERE id= :id")
    suspend fun getNoteById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

}
