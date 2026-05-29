package github.com.pinmarigor.vigia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
                currentRoute?.contains("Home") == true

            Scaffold(

                bottomBar = {
                    if(showBottomBar) {
                        BottomNavigationBar(navController)
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