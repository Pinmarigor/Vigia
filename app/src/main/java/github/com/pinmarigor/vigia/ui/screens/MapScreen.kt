package github.com.pinmarigor.vigia.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import github.com.pinmarigor.vigia.ui.components.Sos
import github.com.pinmarigor.vigia.navigation.Route
import github.com.pinmarigor.vigia.network.model.RouteResult
import github.com.pinmarigor.vigia.network.model.SafetyLevel
import github.com.pinmarigor.vigia.viewmodel.LocationProviderViewModel
import github.com.pinmarigor.vigia.viewmodel.PostViewModel
import github.com.pinmarigor.vigia.viewmodel.RouteProviderViewModel
import java.util.Locale

@Composable
fun MapScreen(
    navController: NavController,
    routeProviderViewModel: RouteProviderViewModel,
    postViewModel: PostViewModel,
    locationViewModel: LocationProviderViewModel
) {
    val recife = LatLng(-8.0476, -34.8770)
    val routes by routeProviderViewModel.routes.collectAsState()
    val searchResults by postViewModel.searchState.collectAsState()

    val currentLocation by locationViewModel.locationState.collectAsState()
    val isLocationLoading by locationViewModel.loadingState.collectAsState()
    val locationError by locationViewModel.errorState.collectAsState()

    // Estados para origem e destino
    var isUsingGPS by remember { mutableStateOf(true) }
    var isSearchingForOrigin by remember { mutableStateOf(false) }

    var originText by remember { mutableStateOf("") }
    var originCoordinates by remember { mutableStateOf<LatLng?>(null) }

    var destinationText by remember { mutableStateOf("") }
    var destinationCoordinates by remember { mutableStateOf<LatLng?>(null) }

    // Estado da rota selecionada
    var selectedRouteIndex by remember(routes) { mutableStateOf(0) }
    var hasCenteredInitially by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recife, 14f)
    }

    // Gerenciador de permissões
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.all { it }
        if (granted) {
            locationViewModel.loadCurrentLocation()
        }
    }

    // Carregar localização inicial e solicitar permissões
    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    // Atualizar origem quando a localização mudar
    LaunchedEffect(currentLocation, isUsingGPS, isLocationLoading, locationError) {
        if (isUsingGPS) {
            originCoordinates = currentLocation
            originText = when {
                isLocationLoading -> "Obtendo localização..."
                locationError != null -> "Erro na localização"
                currentLocation != null -> "Minha localização"
                else -> "Obtendo localização..."
            }
        }

        // Centralizar câmera na primeira vez que a localização for obtida
        if (currentLocation != null && !hasCenteredInitially) {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(currentLocation!!, 15f)
            )
            hasCenteredInitially = true
        }
    }

    // Solicitar cálculo quando os pontos estiverem definidos
    LaunchedEffect(originCoordinates, destinationCoordinates) {
        if (originCoordinates != null && destinationCoordinates != null) {
            routeProviderViewModel.calculateRoute(originCoordinates!!, destinationCoordinates!!)
        }
    }

    // Ajuste de câmera ao receber rotas
    LaunchedEffect(routes) {
        if (routes.isNotEmpty()) {
            val builder = LatLngBounds.Builder()
            routes.forEach { route ->
                route.points.forEach { builder.include(it) }
            }
            try {
                val bounds = builder.build()
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngBounds(bounds, 100)
                )
            } catch (e: Exception) {
                Log.e("MapScreen", "Erro ao ajustar câmera", e)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Marcador de localização atual
            currentLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "Minha Localização",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                )
            }

            routes.forEachIndexed { index, route ->
                val isSelected = index == selectedRouteIndex
                Polyline(
                    points = route.points,
                    color = if (isSelected) Color(0xFF2196F3) else Color.Gray,
                    width = if (isSelected) 15f else 8f,
                    zIndex = if (isSelected) 1f else 0f
                )
            }
        }

        // Painel de busca (Origem e Destino)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                    }
                    Text("Planejar Trajeto", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = originText,
                    onValueChange = {
                        if (!isUsingGPS) {
                            originText = it
                            isSearchingForOrigin = true
                            postViewModel.searchLocation(it)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("De:") },
                    readOnly = isUsingGPS,
                    trailingIcon = {
                        if (isUsingGPS) {
                            IconButton(onClick = {
                                isUsingGPS = false
                                originText = ""
                                originCoordinates = null
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar origem")
                            }
                        } else {
                            IconButton(onClick = {
                                isUsingGPS = true
                                isSearchingForOrigin = false
                                postViewModel.clearSearch()
                                locationViewModel.refreshLocation()
                            }) {
                                Icon(Icons.Default.MyLocation, contentDescription = "Usar minha localização", tint = Color(0xFF2196F3))
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color.LightGray,
                        focusedTextColor = if (isUsingGPS && locationError != null) Color.Red else Color.Black,
                        unfocusedTextColor = if (isUsingGPS && locationError != null) Color.Red else Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = destinationText,
                    onValueChange = {
                        destinationText = it
                        isSearchingForOrigin = false
                        postViewModel.searchLocation(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Para:") },
                    placeholder = { Text("Destino") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color.LightGray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )

                // Resultados da busca
                if (searchResults.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                        items(searchResults) { location ->
                            ListItem(
                                headlineContent = { Text(location.displayName, fontSize = 14.sp, color = Color.Black) },
                                leadingContent = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                                modifier = Modifier.clickable {
                                    if (isSearchingForOrigin) {
                                        originCoordinates = LatLng(location.latitude, location.longitude)
                                        originText = location.displayName
                                    } else {
                                        destinationCoordinates = LatLng(location.latitude, location.longitude)
                                        destinationText = location.displayName
                                    }
                                    postViewModel.clearSearch()
                                }
                            )
                            HorizontalDivider(thickness = 0.5.dp)
                        }
                    }
                }
            }
        }

        // Seletor de rotas em cards
        if (routes.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(routes) { index, route ->
                        RouteCard(
                            route = route,
                            isSelected = index == selectedRouteIndex,
                            onClick = { selectedRouteIndex = index }
                        )
                    }
                }
            }
        }

        Sos(
            onClick = { navController.navigate(Route.SosScreen) },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun RouteCard(
    route: RouteResult,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val safetyColor = when (route.safety.level) {
        SafetyLevel.BAIXO -> Color(0xFF4CAF50) // Verde
        SafetyLevel.MEDIO -> Color(0xFFFFC107) // Amarelo
        SafetyLevel.ALTO -> Color(0xFFF44336)  // Vermelho
    }

    val safetyText = when (route.safety.level) {
        SafetyLevel.BAIXO -> "Rota Segura"
        SafetyLevel.MEDIO -> "Risco Moderado"
        SafetyLevel.ALTO -> "Alto Risco"
    }

    val durationMin = (route.durationSeconds / 60).toInt()
    val distanceKm = String.format(Locale.getDefault(), "%.1f", route.distanceMeters / 1000)

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.White else Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF2196F3)) else null
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(safetyColor)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = safetyText,
                    color = safetyColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "$durationMin min",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
                Text(
                    text = "$distanceKm km",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Score ${route.safety.score.toInt()}",
                fontSize = 11.sp,
                color = Color.DarkGray
            )
            Text(
                text = "${route.safety.influencingPosts} ocorrências",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}
