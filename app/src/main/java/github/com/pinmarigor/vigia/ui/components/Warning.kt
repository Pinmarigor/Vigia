package github.com.pinmarigor.vigia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Warning(
    val modifier: Modifier,
    val imageVector: ImageVector,
    val description: String,
    val backgroundColorIcon: Color,
    val tint: Color,
    val type: String,
    val desc: String,
    val location: String,
    val time: String
)

@Composable
fun Warning(warn: Warning) {
    Box(modifier = warn.modifier) {
        Row() {
            Box(modifier = Modifier
                .background(warn.backgroundColorIcon, shape = RoundedCornerShape(5.dp))
                .padding(8.dp)
            ) {
                Icon(
                    imageVector = warn.imageVector,
                    contentDescription = warn.description,
                    tint = warn.tint
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column() {
                Text(
                    text = warn.type,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = warn.desc,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "location",
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = warn.location,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AccessTimeFilled,
                        contentDescription = "time",
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = warn.time,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}