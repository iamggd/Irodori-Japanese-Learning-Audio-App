package com.iamggd.irodorijapaneselearningaudioapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme.ThemeViewModel

// Define the navigation routes
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object BookContent : Screen("book_content/{bookId}") {
        fun createRoute(bookId: String) = "book_content/$bookId"
    }
}

@Composable
fun IrodoriNavHost(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            // Placeholder: We will build HomeScreen in the next step
            // HomeScreen(navController = navController, themeViewModel = themeViewModel)
        }
        composable(
            route = Screen.BookContent.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            // Extract the passed bookId (A1, A2X, or A2Y)
            val bookId = backStackEntry.arguments?.getString("bookId") ?: "A1"

            // Placeholder: We will build BookContentScreen later
            // BookContentScreen(bookId = bookId, navController = navController)
        }
    }
}