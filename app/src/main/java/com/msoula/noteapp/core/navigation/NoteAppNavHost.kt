package com.msoula.noteapp.core.navigation

import NoteDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.msoula.noteapp.feature.add_note.presentation.AddNoteViewModel
import com.msoula.noteapp.feature.add_note.presentation.screen.AddNoteFormScreen
import com.msoula.noteapp.feature.main.presentation.NoteViewModel
import com.msoula.noteapp.feature.main.presentation.screen.NotesScreen
import com.msoula.noteapp.feature.note_detail.presentation.NoteDetailViewModel

@Composable
fun NoteAppNavHost(navController: NavHostController, navigator: Navigator) {

    NavHost(navController, startDestination = NoteScreenRoute.ROUTE) {
        composable(route = NoteScreenRoute.ROUTE) {
            val notesViewModel = hiltViewModel<NoteViewModel>()
            val notesState by notesViewModel.noteState.collectAsStateWithLifecycle()

            NotesScreen(
                notesState = notesState,
                openAddNoteScreen = { navigator.navigate(NoteFormScreenRoute) },
                openNoteDetail = { id -> navigator.navigate(NoteDetailScreenRoute(id = id.toString())) }
            )
        }

        composable(route = NoteFormScreenRoute.ROUTE) {
            val addNoteViewModel = hiltViewModel<AddNoteViewModel>()
            val addNoteState by addNoteViewModel.addNoteState.collectAsStateWithLifecycle()

            AddNoteFormScreen(state = addNoteState, onUiEvent = addNoteViewModel::onUiEvent)
        }

        composable(route = NoteDetailScreenRoute.route) {
            val noteDetailViewModel = hiltViewModel<NoteDetailViewModel>()
            val noteDetailState by noteDetailViewModel.noteDetailState.collectAsStateWithLifecycle()

            NoteDetailScreen(state = noteDetailState)
        }
    }

}