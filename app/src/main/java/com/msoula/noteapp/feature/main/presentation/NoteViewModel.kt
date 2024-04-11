package com.msoula.noteapp.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msoula.noteapp.feature.main.data.state.NotesFeedUiState
import com.msoula.noteapp.feature.main.domain.use_case.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    getNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    val noteState: StateFlow<NotesFeedUiState> = getNotesUseCase()
        .map { list ->
            if (list.isEmpty()) {
                NotesFeedUiState.Empty
            } else {
                NotesFeedUiState.Success(list)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesFeedUiState.Loading)
}