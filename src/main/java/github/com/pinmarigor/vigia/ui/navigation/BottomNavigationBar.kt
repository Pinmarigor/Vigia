package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import github.com.pinmarigor.vigia.ui.theme.DarkBlue

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier.height(100.dp),
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

//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(Route.Community)
//            },
//            icon = {
//                Icon(Icons.Default.List, null)
//            },
//            label = {
//                Text("Comunidade")
//            }
//        )
//
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(Route.Alerts)
//            },
//            icon = {
//                Icon(Icons.Default.Notifications, null)
//            },
//            label = {
//                Text("Avisos")
//            }
//        )
//
    }
}