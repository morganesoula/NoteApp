package com.msoula.noteapp.feature.main.domain.use_case

import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.core.util.Result
import com.msoula.noteapp.feature.main.data.NoteDataSource

class InsertNoteUseCase(private val noteDataSource: NoteDataSource) {
    suspend operator fun invoke(note: NoteEntity): Result<Boolean> = noteDataSource.upsertNote(note)
}