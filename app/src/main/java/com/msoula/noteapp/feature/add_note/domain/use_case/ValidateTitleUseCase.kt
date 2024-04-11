package com.msoula.noteapp.feature.add_note.domain.use_case

import com.msoula.noteapp.R
import com.msoula.noteapp.core.use_case.ValidationResult

class ValidateTitleUseCase {

    fun execute(title: String): ValidationResult {
        if (title.isEmpty()) {
            return ValidationResult(false, R.string.title_error)
        }

        return ValidationResult(true)
    }
}