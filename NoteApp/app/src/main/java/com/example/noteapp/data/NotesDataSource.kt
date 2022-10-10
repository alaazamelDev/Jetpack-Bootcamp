package com.example.noteapp.data

import com.example.noteapp.models.Note

class NotesDataSource {

    fun loadNotes(): List<Note> {
        return listOf<Note>(
            Note(
                title = "Good Morning",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "My Goal for today",
                content = "my aim for today is to write my first Medium Blog"
            ),
            Note(
                title = "Hello",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "Good Morning",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "My Goal for today",
                content = "my aim for today is to write my first Medium Blog"
            ),
            Note(
                title = "Hello",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "Good Morning",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "My Goal for today",
                content = "my aim for today is to write my first Medium Blog"
            ),
            Note(
                title = "Hello",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "Good Morning",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
            Note(
                title = "My Goal for today",
                content = "my aim for today is to write my first Medium Blog"
            ),
            Note(
                title = "Hello",
                content = "Hello Man, you have to be patient and hard worker and sure you will get the result"
            ),
        )
    }

}