package it.icsone.listfromapicompose.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import it.icsone.listfromapicompose.ui.commons.MyTopAppBar
import it.icsone.listfromapicompose.ui.home.HomeView
import it.icsone.listfromapicompose.ui.settings.SettingsView

@Composable
fun Dashboard(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Settings
    )

    val currentIndex = remember {
        mutableStateOf(items.indexOf(BottomNavItem.Home))
    }

    Scaffold(
        topBar = {
            MyTopAppBar(navController = navController, title = "My App")
        },
        bottomBar = {
            NavigationBar {

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = "") },
                        label = { Text(text = item.title) },
                        selected = currentIndex.value == index,
                        onClick = {
                            currentIndex.value = index
                        }
                    )
                }
            }
        },
        /*floatingActionButton = {
            if (currentIndex.value == 0) FloatingActionButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "")
            }
        },*/

    ) { padding ->

        when (currentIndex.value) {
            0 -> HomeView(navController = navController, padding = padding)
            1 -> SettingsView(navController = navController)
        }
    }
}

sealed class BottomNavItem(val icon: ImageVector, val title: String) {
    data object Home : BottomNavItem(Icons.Filled.Home, "Home")
    data object Settings : BottomNavItem(Icons.Filled.Settings, "Settings")
}