package com.msoula.noteapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msoula.noteapp.core.database.dao.NoteDAO
import com.msoula.noteapp.core.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteAppDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO
}