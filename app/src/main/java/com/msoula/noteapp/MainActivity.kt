package com.msoula.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.rememberNavController
import com.msoula.noteapp.core.navigation.Navigator
import com.msoula.noteapp.core.navigation.NoteAppNavHost
import com.msoula.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                NoteApp(navigator = navigator)
            }
        }
    }
}

@Composable
fun NoteApp(
    navigator: Navigator
) {
    val navController = rememberNavController()

    DisposableEffect(key1 = navController) {
        navigator.setController(navController)
        onDispose {
            navigator.clear()
        }
    }

    NoteAppNavHost(navController = navController, navigator = navigator)
}