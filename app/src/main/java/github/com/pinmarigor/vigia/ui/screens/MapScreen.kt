package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import github.com.pinmarigor.vigia.ui.components.Sos
import github.com.pinmarigor.vigia.navigation.Route

@Composable
fun MapScreen(navController: NavController) {
    val recife = LatLng(-8.0476, -34.8770)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recife, 14f)
    }

    Box{
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )

        FloatingActionButton(
            onClick = {
                navController.navigate(Route.Home)
            },
            containerColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 35.dp, end = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.Black
            )
        }

        Sos(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}