package github.com.pinmarigor.vigia.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Sos(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.Red,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Sos,
            contentDescription = "SOS",
            tint = Color.White
        )
    }
}