package github.com.pinmarigor.vigia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import github.com.pinmarigor.vigia.ui.components.Sos
import github.com.pinmarigor.vigia.ui.navigation.BottomNavigationBar
import github.com.pinmarigor.vigia.ui.navigation.MainNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentRoute = navBackStackEntry?.destination?.route

            val showBottomBar =
                currentRoute?.contains("Home") == true ||
                currentRoute?.contains("Community") == true ||
                currentRoute?.contains("Warnings") == true ||
                currentRoute?.contains("Contacts") == true ||
                currentRoute?.contains("Configs") == true

            Scaffold(

                bottomBar = {
                    if(showBottomBar) {
                        BottomNavigationBar(navController)
                    }
                },
                floatingActionButton = {
                    if (showBottomBar) {
                        Sos(onClick = {})
                    }
                }

            ) { padding ->

                Box(
                    modifier = Modifier.padding(padding)
                ) {

                    MainNavHost(
                        navController = navController
                    )
                }
            }
        }
    }
}