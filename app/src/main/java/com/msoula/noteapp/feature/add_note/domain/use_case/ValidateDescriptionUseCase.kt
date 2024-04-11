package com.msoula.noteapp.feature.add_note.domain.use_case

import com.msoula.noteapp.R
import com.msoula.noteapp.core.use_case.ValidationResult

class ValidateDescriptionUseCase {

    fun execute(description: String): ValidationResult {
        if (description.isEmpty()) {
            return ValidationResult(false, R.string.description_error)
        }

        return ValidationResult(true)
    }
}