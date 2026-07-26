package github.com.pinmarigor.vigia.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier.height(100.dp)
            .border(
                width = 1.dp,
                color = GradientMiddle
            ),
        containerColor = DarkBlue
    ) {

        NavigationBarItem(
            selected = navController.currentDestination?.route?.contains("Home") == true,
            onClick = {
                navController.navigate(Route.Home) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route?.contains("MapScreen") == true,
            onClick = {
                navController.navigate(Route.MapScreen) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.Map , null)
            },
            label = {
                Text("Mapa")
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route?.contains("Community") == true,
            onClick = {
                navController.navigate(Route.Community) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.List, null)
            },
            label = {
                Text("Comunidade")
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route?.contains("Warnings") == true,
            onClick = {
                navController.navigate(Route.Warnings) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.Notifications, null)
            },
            label = {
                Text("Avisos")
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route?.contains("Configs") == true,
            onClick = {
                navController.navigate(Route.Configs) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.Settings, null)
            },
            label = {
                Text("Config")
            }
        )
    }
}
