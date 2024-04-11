package com.msoula.noteapp.core.navigation

import androidx.lifecycle.SavedStateHandle

object NoteScreenRoute : NavigationRoute {
    override fun buildRoute(): String = ROUTE

    private const val ROOT = "notes_screen"
    const val ROUTE = ROOT
}

object NoteFormScreenRoute : NavigationRoute {
    override fun buildRoute(): String = ROUTE

    private const val ROOT = "note_form_screen"
    const val ROUTE = ROOT
}

data class NoteDetailScreenRoute(val id: String) : NavigationRoute {

    constructor(savedStateHandle: SavedStateHandle) : this(
        id = requireNotNull(savedStateHandle.get<String>(inputArg))
    )

    override fun buildRoute(): String = "$root/$id"

    companion object {
        private const val root = "note_detail_screen"
        private const val inputArg = "input"

        const val route = "$root/{$inputArg}"
    }
}