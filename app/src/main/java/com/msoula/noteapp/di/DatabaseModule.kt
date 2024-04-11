package com.msoula.noteapp.di

import android.content.Context
import androidx.room.Room
import com.msoula.noteapp.core.database.NoteAppDatabase
import com.msoula.noteapp.core.database.dao.NoteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteAppDatabase =
        Room.databaseBuilder(context, NoteAppDatabase::class.java, "note-app-database")
            .build()

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteAppDatabase): NoteDAO =
        db.noteDAO()
}