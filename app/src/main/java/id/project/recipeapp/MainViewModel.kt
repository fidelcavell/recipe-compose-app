package id.project.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())

    // Exposed variable to get used outside this 'MainViewModel' class :
    val categoriesState: State<RecipeState> = _categoriesState

    // To Call Code / Function when MainViewModel class is loaded :
    init {
        fetchCategories()
    }

    // Function to fetch or getting categories data from api / internet services :
    private fun fetchCategories() {
        viewModelScope.launch { // Starting point whenever use suspend thing - 'CoroutineScope' / 'launch'
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            } catch(e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error Fetching Categories ${e.message}"
                )
            }
        }
    }

    // To inform the state of Recipe whether it provided or not :
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}