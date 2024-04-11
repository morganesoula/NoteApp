package com.msoula.noteapp.feature.note_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msoula.noteapp.core.navigation.NoteDetailScreenRoute
import com.msoula.noteapp.feature.note_detail.data.NoteDetailUiState
import com.msoula.noteapp.feature.note_detail.domain.use_case.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _noteDetailState = MutableStateFlow(NoteDetailUiState())
    val noteDetailState = _noteDetailState.asStateFlow()

    private val route = NoteDetailScreenRoute(savedStateHandle)
    private val savedId: Long = route.id.toLong()

    init {
        fetchNoteInDb()
    }

    private fun fetchNoteInDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = getNoteByIdUseCase(savedId)
            _noteDetailState.update { state ->
                state.copy(
                    title = note.data?.title ?: "",
                    description = note.data?.description ?: ""
                )
            }
        }
    }
}