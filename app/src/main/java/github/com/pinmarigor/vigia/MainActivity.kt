package github.com.pinmarigor.vigia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.repositories.LocationRepository
import github.com.pinmarigor.vigia.data.repositories.PostRepository
import github.com.pinmarigor.vigia.data.repositories.RoutingRepository
import github.com.pinmarigor.vigia.data.repositories.UserRepository
import github.com.pinmarigor.vigia.ui.components.Sos
import github.com.pinmarigor.vigia.navigation.BottomNavigationBar
import github.com.pinmarigor.vigia.navigation.MainNavHost
import github.com.pinmarigor.vigia.navigation.Route
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import github.com.pinmarigor.vigia.network.retrofit.RetrofitClient
import github.com.pinmarigor.vigia.ui.screens.SosScreen
import github.com.pinmarigor.vigia.utils.RouteSafetyCalculator
import github.com.pinmarigor.vigia.viewmodel.AuthViewModel
import github.com.pinmarigor.vigia.viewmodel.LocationProviderViewModel
import github.com.pinmarigor.vigia.viewmodel.PostViewModel
import github.com.pinmarigor.vigia.viewmodel.RouteProviderViewModel
import github.com.pinmarigor.vigia.viewmodel.factory.AuthViewModelFactory
import github.com.pinmarigor.vigia.viewmodel.factory.LocationProviderViewModelFactory
import github.com.pinmarigor.vigia.viewmodel.factory.PostViewModelFactory
import github.com.pinmarigor.vigia.viewmodel.factory.RouteProviderViewModelFactory
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val fbDatabase = FBDatabase()
            val userRepository = UserRepository(fbDatabase)
            val nominatimRepository = NominatimRepository(RetrofitClient.api)
            val postRepository = PostRepository(fbDatabase)
            val routingRepository = RoutingRepository(
                RetrofitClient.routingApi,
                postRepository,
                RouteSafetyCalculator()
            )

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            val locationRepository = LocationRepository(fusedLocationClient)


            val authFactory = AuthViewModelFactory(userRepository)
            val postFactory = PostViewModelFactory(nominatimRepository, postRepository)
            val routeProviderFactory = RouteProviderViewModelFactory(routingRepository)
            val locationFactory = LocationProviderViewModelFactory(locationRepository)

            val authViewModel: AuthViewModel = viewModel(factory = authFactory)
            val postViewModel: PostViewModel = viewModel(factory = postFactory)
            val routeProviderViewModel: RouteProviderViewModel = viewModel(factory = routeProviderFactory)
            val locationViewModel: LocationProviderViewModel = viewModel(factory = locationFactory)

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
                        Sos(onClick = {navController.navigate(Route.SosScreen)})
                    }
                }

            ) { padding ->

                Box(
                    modifier = Modifier.padding(padding)
                ) {

                    MainNavHost(
                        navController = navController,
                        authViewModel = authViewModel,
                        postViewModel = postViewModel,
                        routeViewModel = routeProviderViewModel,
                        locationViewModel = locationViewModel
                    )
                }
            }
        }
    }
}
