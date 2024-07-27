package id.project.recipeapp

// Act as a constant that handle route :
sealed class Screen(val route: String) {
    object RecipeScreen: Screen("recipeScreen")
    object DetailScreen: Screen("detailScreen")
}