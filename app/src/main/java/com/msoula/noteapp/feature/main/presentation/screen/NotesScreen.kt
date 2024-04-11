package com.msoula.noteapp.feature.main.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.feature.main.data.state.NotesFeedUiState

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    notesState: NotesFeedUiState,
    openAddNoteScreen: () -> Unit,
    openNoteDetail: (id: Long) -> Unit
) {
    Scaffold(modifier, floatingActionButton = {
        FloatingActionButton(onClick = { openAddNoteScreen() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add new note FAB")
        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            when (notesState) {
                is NotesFeedUiState.Loading -> CircularProgressIndicator()
                is NotesFeedUiState.Empty -> NoNotes()
                is NotesFeedUiState.Success -> NoteContent(
                    notes = notesState.notesFeed,
                    openNoteDetail = openNoteDetail
                )
            }
        }
    }
}

@Composable
fun NoNotes(modifier: Modifier = Modifier) {
    Text(modifier = modifier.fillMaxSize(), text = "No notes found", textAlign = TextAlign.Center)
}

@Composable
fun NoteContent(
    modifier: Modifier = Modifier,
    notes: List<NoteEntity>,
    openNoteDetail: (id: Long) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteRowSingleContent(note = note, openNoteDetail = openNoteDetail)
        }
    }
}

@Composable
fun NoteRowSingleContent(
    modifier: Modifier = Modifier,
    note: NoteEntity,
    openNoteDetail: (id: Long) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 8.dp, end = 8.dp)
            .clickable {
                openNoteDetail(note.id)
            }
    ) {
        Column {
            Text(
                text = note.title, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}