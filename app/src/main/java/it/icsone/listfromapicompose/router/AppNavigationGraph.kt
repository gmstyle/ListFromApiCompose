package it.icsone.listfromapicompose.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.icsone.listfromapicompose.ui.detail.DetailView
import it.icsone.listfromapicompose.ui.home.HomeView
import it.icsone.listfromapicompose.ui.settings.SettingsView

@Composable
fun AppNavigationGraph(
    navHostController: NavHostController
) {
    //val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Route.Home.route
    ) {
        addHomeRoute(navHostController)
        addDetailRoute(navHostController)
        addSettingsRoute(navHostController)
    }
}

private fun NavGraphBuilder.addHomeRoute(
    navController: NavController
) {
    composable(Route.Home.route) {
        HomeView(navController = navController)
    }
}

private fun NavGraphBuilder.addDetailRoute(navController: NavController) {
    composable(Route.Detail.route) {
        DetailView(navController = navController)
    }
}

private fun NavGraphBuilder.addSettingsRoute(navController: NavController) {
    composable(Route.Settings.route) {
        SettingsView(navController = navController)
    }
}


sealed class Route(val route: String) {
    data object Home : Route("home")
    data object Detail : Route("detail")
    data object Settings : Route("settings")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}