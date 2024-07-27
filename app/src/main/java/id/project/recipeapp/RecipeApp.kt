package id.project.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToDetailScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "category",
                    it
                ) // To Save Category data as Key - Value Pair Type and save it on saveStateHandle and get passed to other Screen. Init as Entry Point of BackStack
                navController.navigate(route = Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category") // To get value based on key that stored at savedHandleState from previous BackStack / Screen
                    ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}