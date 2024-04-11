package com.msoula.noteapp.feature.add_note.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.core.navigation.Navigator
import com.msoula.noteapp.core.navigation.NoteScreenRoute
import com.msoula.noteapp.core.util.Result
import com.msoula.noteapp.feature.add_note.data.AddNoteFormState
import com.msoula.noteapp.feature.add_note.domain.use_case.ValidateInputUseCase
import com.msoula.noteapp.feature.main.domain.use_case.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val validateInputUseCase: ValidateInputUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _addNoteState = MutableStateFlow(AddNoteFormState())
    val addNoteState = _addNoteState.asStateFlow()

    fun onUiEvent(event: AddNoteFormEvent) {
        when (event) {
            is AddNoteFormEvent.OnTitleChanged -> {
                _addNoteState.update { it.copy(noteTitle = event.title) }
                validateInput()
            }

            is AddNoteFormEvent.OnDescriptionChanged -> {
                _addNoteState.update { it.copy(noteDescription = event.description) }
                validateInput()
            }

            AddNoteFormEvent.OnSubmitNote -> {
                saveNote()
            }
        }
    }

    private fun validateInput() {
        val titleResult =
            validateInputUseCase.validateTitleUseCase.execute(addNoteState.value.noteTitle)
        val descriptionResult =
            validateInputUseCase.validateDescriptionUseCase.execute(addNoteState.value.noteDescription)

        val error = listOf(titleResult, descriptionResult).any { !it.successful }

        _addNoteState.update { it.copy(enableSubmit = !error) }
    }

    private fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            when (insertNoteUseCase(
                NoteEntity(
                    0,
                    addNoteState.value.noteTitle,
                    addNoteState.value.noteDescription
                )
            )) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        navigator.navigate(NoteScreenRoute)
                    }
                }

                is Result.Error -> Log.e("NotApp", "Error while inserting note")
            }
        }
    }

}