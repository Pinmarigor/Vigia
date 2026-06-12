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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.navigation.Route

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
fun Post (post: Post, navController: NavController) {
    var countLike by remember { mutableStateOf(0) }
    var countComment by remember { mutableStateOf(0) }
    var like by remember { mutableStateOf(false) }

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
                        onClick = {
                            like = !like

                            if(like) {
                                countLike++
                            } else {
                                countLike--
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00326C)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                            tint = Color(0xFF509AF8)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = countLike.toString(),
                            color = Color(0xFF509AF8)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(
                        onClick = {
                            navController.navigate(Route.Comments)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00326C)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChatBubbleOutline,
                            contentDescription = null,
                            tint = Color(0xFF509AF8)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = countComment.toString(),
                            color = Color(0xFF509AF8)
                        )
                    }
                }
            }
        }
    }
}