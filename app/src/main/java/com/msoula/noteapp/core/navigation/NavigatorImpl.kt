package com.msoula.noteapp.core.navigation

import androidx.navigation.NavController

class NavigatorImpl : Navigator {
    private var navController: NavController? = null

    override fun setController(navController: NavController) {
        this.navController = navController
    }

    override fun navigate(route: NavigationRoute) {
        navController?.navigate(route.buildRoute())
    }

    override fun clear() {
        navController = null
    }
}