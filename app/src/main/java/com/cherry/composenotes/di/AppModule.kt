package com.cherry.composenotes.di

import androidx.room.Room
import com.cherry.composenotes.data.NoteRepositoryImpl
import com.cherry.composenotes.data.local.NoteDatabase
import com.cherry.composenotes.domain.NoteRepository
import com.cherry.composenotes.presentation.NoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    single<NoteRepository> { NoteRepositoryImpl(get()) }

    viewModel { NoteViewModel(get()) }

}