package com.msoula.noteapp.feature.add_note.data

data class AddNoteFormState(
    val noteId: Long? = null,
    val noteTitle: String = "",
    val noteTitleError: Int? = null,
    val noteDescription: String = "",
    val noteDescriptionError: Int? = null,
    val enableSubmit: Boolean = false
)