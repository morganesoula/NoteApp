package com.msoula.noteapp.feature.main.data

import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.core.util.Result
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun deleteNote(noteId: Long)
    suspend fun upsertNote(note: NoteEntity): Result<Boolean>
    suspend fun getNoteById(noteId: Long): Result<NoteEntity>
}