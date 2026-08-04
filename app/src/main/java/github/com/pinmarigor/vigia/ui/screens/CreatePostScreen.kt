package github.com.pinmarigor.vigia.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.data.model.Post
import github.com.pinmarigor.vigia.data.model.PostType
import github.com.pinmarigor.vigia.network.model.SearchLocation
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.ui.theme.LightBLue
import github.com.pinmarigor.vigia.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    navController: NavController,
    postViewModel: PostViewModel
) {
    var selectedType by remember { mutableStateOf(PostType.OUTRO) }
    var description by remember { mutableStateOf("") }
    val selectedLocation by postViewModel.selectedLocationState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Post", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlue)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DarkBlue, GradientMiddle, DarkBlue)
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                PostTypeSelector(
                    selectedType = selectedType,
                    onTypeSelected = { selectedType = it }
                )

                DescriptionField(
                    description = description,
                    onDescriptionChange = { description = it }
                )

                LocationSection(
                    postViewModel = postViewModel,
                    selectedLocation = selectedLocation
                )

                PublishButton(
                    enabled = description.isNotBlank() && selectedLocation != null,
                    onClick = {
                        val newPost = Post(
                            description = description,
                            type = selectedType,
                            latitude = selectedLocation?.latitude,
                            longitude = selectedLocation?.longitude,
                            locationName = selectedLocation?.displayName ?: ""
                        )
                        Log.d("POST", "Novo post criado: $newPost")
                        // TODO: Enviar newPost para o Firebase
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTypeSelector(
    selectedType: PostType,
    onTypeSelected: (PostType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Tipo de Ocorrência",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedType.name.replace("_", " "),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = LightBLue,
                    unfocusedBorderColor = Color.Gray
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(GradientMiddle)
            ) {
                PostType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name.replace("_", " "), color = Color.White) },
                        onClick = {
                            onTypeSelected(type)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DescriptionField(
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column {
        Text(
            text = "Descrição",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { Text("O que aconteceu?", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = LightBLue,
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}

@Composable
fun LocationSection(
    postViewModel: PostViewModel,
    selectedLocation: SearchLocation?
) {
    Column {
        Text(
            text = "Localização",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (selectedLocation == null) {
            SearchField(onSearch = { postViewModel.searchLocation(it) })
            SearchResultList(
                postViewModel = postViewModel,
                onLocationSelected = { postViewModel.selectLocation(it) }
            )
        } else {
            SelectedLocationCard(
                location = selectedLocation,
                onClear = { postViewModel.selectLocation(null) } // Reutilizando selectLocation para limpar
            )
        }
    }
}

@Composable
fun SearchField(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("🔍 Pesquisar localização...", color = Color.Gray) },
        trailingIcon = {
            IconButton(onClick = { if (query.isNotBlank()) onSearch(query) }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar", tint = LightBLue)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = LightBLue,
            unfocusedBorderColor = Color.Gray
        ),
        singleLine = true
    )
}

@Composable
fun SearchResultList(
    postViewModel: PostViewModel,
    onLocationSelected: (SearchLocation) -> Unit
) {
    val results by postViewModel.searchState.collectAsState()

    if (results.isNotEmpty()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .heightIn(max = 200.dp),
            colors = CardDefaults.cardColors(containerColor = GradientMiddle),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            LazyColumn {
                items(results) { location ->
                    ListItem(
                        headlineContent = { Text(location.displayName, color = Color.White, fontSize = 14.sp) },
                        leadingContent = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = LightBLue) },
                        modifier = Modifier.clickable { onLocationSelected(location) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                    )
                    HorizontalDivider(color = Color.DarkGray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun SelectedLocationCard(
    location: SearchLocation,
    onClear: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0x3300BFFF)),
        border = BorderStroke(1.dp, LightBLue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.Green)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Local selecionado", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = location.displayName, color = Color.LightGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = onClear,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Alterar localização", color = LightBLue)
            }
        }
    }
}

@Composable
fun PublishButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBLue,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = enabled
    ) {
        Text(
            text = "Publicar",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
