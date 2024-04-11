package com.msoula.noteapp.core.navigation

import androidx.navigation.NavController

interface Navigator {

    fun setController(navController: NavController)
    fun navigate(route: NavigationRoute)
    fun clear()
}