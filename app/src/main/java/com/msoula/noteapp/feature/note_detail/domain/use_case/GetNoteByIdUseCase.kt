package com.msoula.noteapp.feature.note_detail.domain.use_case

import com.msoula.noteapp.feature.main.data.NoteDataSource

class GetNoteByIdUseCase(private val noteDataSource: NoteDataSource) {
    suspend operator fun invoke(noteId: Long) = noteDataSource.getNoteById(noteId)
}