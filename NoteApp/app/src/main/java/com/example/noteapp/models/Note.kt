package com.example.noteapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),   // Auto generate uuid
    val title: String,
    val content: String,
    val entryDate: LocalDateTime = LocalDateTime.now(),
)
