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
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
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

class Post (
    val modifier: Modifier,
    val imageVector: ImageVector,
    val description: String,
    val backgroundColorIcon: Color,
    val type: String,
    val desc: String,
    val location: String,
    val time: String
)

@Composable
fun Post (post: Post) {
    Box(
        modifier = post.modifier
    ) {
        Row() {
            Box(modifier = Modifier
                .background(post.backgroundColorIcon, shape = RoundedCornerShape(2.dp))
                .padding(8.dp)
            ) {
                Icon(
                    imageVector = post.imageVector,
                    contentDescription = post.description,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column() {
                Text(
                    text = post.type,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = post.desc,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "location",
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = post.location,
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
                        text = post.time,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row() {
                    Button(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                        )
                        Text(
                            text = "45"
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChatBubbleOutline,
                            contentDescription = null,
                        )
                        Text(
                            text = "45"
                        )
                    }
                }
            }
        }
    }
}