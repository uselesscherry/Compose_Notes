package com.cherry.composenotes.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val title: String,
    val content: String,
)
