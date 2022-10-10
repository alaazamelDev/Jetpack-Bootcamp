package com.example.noteapp.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.models.Note
import com.example.noteapp.widgets.DecoratedButton
import com.example.noteapp.widgets.InputField
import java.time.format.DateTimeFormatter


@Composable
fun MainScreen(
    navController: NavController,
    notes: List<Note>,
) {
    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                IconButton(
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "notifications"
                    )
                }
            })
    }) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                label = "Title",
                onTextChanged = {
                    if (it.all { ch -> ch.isLetter() || ch.isWhitespace() })
                        title = it
                }
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = content,
                label = "Content",
                onTextChanged = {
                    if (it.all { ch -> ch.isLetter() || ch.isWhitespace() })
                        content = it
                }
            )
            DecoratedButton(
                modifier = Modifier.padding(top = 16.dp),
                label = "Save",
                onClick = {
                    if (title.isNotEmpty() && content.isNotEmpty()) {
                        // add this note to the list
                        title = ""
                        content = ""
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp),
            ) {
                items(items = notes) { item ->
                    NoteCard(note = item) {
                        // OnClick Event Handler
                            note ->
                        Log.d("NoteCard", "MainScreen: $note")
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun NoteCard(note: Note = NotesDataSource().loadNotes()[0], onClicked: (Note) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable { onClicked(note) },
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
        ) {
            Text(text = note.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = DateTimeFormatter.ofPattern("yyyy:mm:dd").format(note.entryDate),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}