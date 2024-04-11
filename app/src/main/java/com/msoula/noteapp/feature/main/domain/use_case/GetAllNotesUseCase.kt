package com.msoula.noteapp.feature.main.domain.use_case

import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.feature.main.data.NoteDataSource
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val noteDataSource: NoteDataSource) {
    operator fun invoke(): Flow<List<NoteEntity>> = noteDataSource.getNotes()
}