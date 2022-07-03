package com.cherry.composenotes.di

import android.app.Application
import androidx.room.Room
import com.cherry.composenotes.data.local.NoteDatabase
import com.cherry.composenotes.data.local.NoteRepositoryImpl
import com.cherry.composenotes.domain.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    @Named("NoteRepo")
    fun provideNoteRepository(db:NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(db)
    }

}