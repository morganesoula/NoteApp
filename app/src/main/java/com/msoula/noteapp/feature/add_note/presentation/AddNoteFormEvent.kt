package com.msoula.noteapp.feature.add_note.presentation

sealed interface AddNoteFormEvent {

    data class OnTitleChanged(val title: String) : AddNoteFormEvent
    data class OnDescriptionChanged(val description: String) : AddNoteFormEvent
    data object OnSubmitNote : AddNoteFormEvent
}