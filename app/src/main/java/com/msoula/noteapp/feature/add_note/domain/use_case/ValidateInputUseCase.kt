package com.msoula.noteapp.feature.add_note.domain.use_case

data class ValidateInputUseCase(
    val validateTitleUseCase: ValidateTitleUseCase,
    val validateDescriptionUseCase: ValidateDescriptionUseCase
)
