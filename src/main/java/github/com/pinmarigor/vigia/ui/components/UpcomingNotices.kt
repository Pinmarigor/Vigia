package github.com.pinmarigor.vigia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnpcomingNotices(type: String, location: String, time: String, modifier: Modifier, imageVector: ImageVector, description: String, color: Color) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column() {
                Box (
                    modifier = Modifier
                        .background(color = color, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                )
                {
                    // Argumento
                    Icon(
                        imageVector = imageVector,
                        contentDescription = description
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column() {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = type,
                        // argumento
                        color = color,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = time,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = location,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}