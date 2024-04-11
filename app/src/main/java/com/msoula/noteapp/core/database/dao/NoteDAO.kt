package com.msoula.noteapp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.msoula.noteapp.core.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM noteentity")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Upsert
    suspend fun upsertNote(note: NoteEntity)

    @Query("DELETE FROM noteentity where id = :noteId")
    suspend fun deleteNote(noteId: Long)

    @Query("SELECT * FROM noteentity WHERE id = :noteId")
    suspend fun getNoteByNoteId(noteId: Long): NoteEntity
}