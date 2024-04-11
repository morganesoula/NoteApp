package com.msoula.noteapp.feature.main.data.state

import com.msoula.noteapp.core.database.entity.NoteEntity

sealed interface NotesFeedUiState {

    data object Loading : NotesFeedUiState
    data object Empty : NotesFeedUiState
    data class Success(
        val notesFeed: List<NoteEntity>
    ) : NotesFeedUiState
}