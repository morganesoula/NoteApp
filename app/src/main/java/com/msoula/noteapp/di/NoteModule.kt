package com.msoula.noteapp.di

import com.msoula.noteapp.core.database.dao.NoteDAO
import com.msoula.noteapp.feature.add_note.domain.use_case.ValidateDescriptionUseCase
import com.msoula.noteapp.feature.add_note.domain.use_case.ValidateInputUseCase
import com.msoula.noteapp.feature.add_note.domain.use_case.ValidateTitleUseCase
import com.msoula.noteapp.feature.main.data.NoteDataSource
import com.msoula.noteapp.feature.main.data.NoteDataSourceImpl
import com.msoula.noteapp.feature.main.domain.use_case.GetAllNotesUseCase
import com.msoula.noteapp.feature.main.domain.use_case.InsertNoteUseCase
import com.msoula.noteapp.feature.note_detail.domain.use_case.GetNoteByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDataSource(noteDAO: NoteDAO): NoteDataSource =
        NoteDataSourceImpl(noteDAO)

    @Provides
    fun provideGetAllNotesUseCase(noteDataSource: NoteDataSource): GetAllNotesUseCase =
        GetAllNotesUseCase(noteDataSource)

    @Provides
    fun provideInsertNoteUseCase(noteDataSource: NoteDataSource): InsertNoteUseCase =
        InsertNoteUseCase(noteDataSource)

    @Provides
    fun provideGetNoteByIdUseCase(noteDataSource: NoteDataSource): GetNoteByIdUseCase =
        GetNoteByIdUseCase(noteDataSource)

    @Provides
    fun provideValidateInputUseCase(): ValidateInputUseCase =
        ValidateInputUseCase(
            ValidateTitleUseCase(),
            ValidateDescriptionUseCase()
        )
}