package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SosScreen(navController: NavController) {
    Icon(
        imageVector = Icons.Default.Sos,
        contentDescription = "SOS",
        tint = Color.White,
        modifier = Modifier.size(42.dp)
    )
}