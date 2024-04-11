package com.msoula.noteapp.feature.main.data

import com.msoula.noteapp.core.database.dao.NoteDAO
import com.msoula.noteapp.core.database.entity.NoteEntity
import com.msoula.noteapp.core.util.Result
import kotlinx.coroutines.flow.Flow

class NoteDataSourceImpl(private val noteDAO: NoteDAO) : NoteDataSource {

    override fun getNotes(): Flow<List<NoteEntity>> =
        noteDAO.getAllNotes()

    override suspend fun deleteNote(noteId: Long) =
        noteDAO.deleteNote(noteId)

    override suspend fun upsertNote(note: NoteEntity): Result<Boolean> =
        try {
            noteDAO.upsertNote(note)
            Result.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(
                throwable = e.cause ?: Throwable(message = "Could not upsert note")
            )
        }

    override suspend fun getNoteById(noteId: Long): Result<NoteEntity> =
        try {
            val note = noteDAO.getNoteByNoteId(noteId)
            Result.Success(note)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(
                throwable = e.cause ?: Throwable(message = "Could not get note with id: $noteId")
            )
        }

}

