package com.msoula.noteapp.core.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)