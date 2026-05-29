package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import github.com.pinmarigor.vigia.ui.theme.DarkBlue

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar (
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
//        NavigationBarItem(
//            selected = false,
//            onClick = {
//                navController.navigate(Route.Contacts)
//            },
//            icon = {
//                Icon(Icons.Default.Person, null)
//            },
//            label = {
//                Text("Contatos")
//            }
//        )
    }
}