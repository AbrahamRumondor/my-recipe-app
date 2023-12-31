package com.tutorial.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate, navigateToDetail = {
                // This part is responsible for passing from the current screen to the detail screen.
                // It utilizes the savedStateHandle, which is a component of the compose navigation framework.

                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route){
            // logikanya disini kita akses yang disimpan di stack/screen sebelumnya trus kita pakai.
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                    get<Category>("cat") ?: Category("", "", "", "")

            CategoryDetailScreen(category = category)
        }
    }
}