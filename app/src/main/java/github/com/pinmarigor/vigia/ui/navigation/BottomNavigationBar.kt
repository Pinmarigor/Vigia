package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
            selected = false,
            onClick = {
                navController.navigate(Route.Home)
            },
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Route.MapScreen)
            },
            icon = {
                Icon(Icons.Default.Map , null)
            },
            label = {
                Text("Mapa")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Route.Community)
            },
            icon = {
                Icon(Icons.Default.List, null)
            },
            label = {
                Text("Comunidade")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Route.Warnings)
            },
            icon = {
                Icon(Icons.Default.Notifications, null)
            },
            label = {
                Text("Avisos")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Route.Configs)
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