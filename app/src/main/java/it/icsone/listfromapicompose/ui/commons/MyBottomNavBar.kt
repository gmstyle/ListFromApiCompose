package it.icsone.listfromapicompose.ui.commons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import it.icsone.listfromapicompose.router.Route

@Composable
fun MyBottomNavBar(navController: NavController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Settings
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                /*alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),*/
                onClick = {
                    if (currentRoute != item.route) navController.navigate(item.route)
                }
            )
        }
    }

}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    data object Home : BottomNavItem(Route.Home.route, Icons.Filled.Home, "Home")
    data object Settings : BottomNavItem(Route.Settings.route, Icons.Filled.Settings, "Settings")
}