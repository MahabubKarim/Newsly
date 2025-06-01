package com.mmk.newsly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mmk.newsly.ui.screens.details.DetailScreen
import com.mmk.newsly.ui.screens.home.HomeScreen
import com.mmk.newsly.ui.screens.saved.SavedScreen
import com.mmk.newsly.viewmodel.NewsViewModel

@Composable
fun NewslyNavHost(
    navController: NavHostController,
    viewModel: NewsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    navController.navigate("details/${article.url}")
                }
            )
        }

        composable("details/{articleUrl}") { backStackEntry ->
            val articleUrl = backStackEntry.arguments?.getString("articleUrl") ?: ""
            DetailScreen(
                articleUrl = articleUrl,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("saved") {
            SavedScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    navController.navigate("details/${article.url}")
                }
            )
        }
    }
}