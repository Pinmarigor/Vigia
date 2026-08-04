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
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
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
import github.com.pinmarigor.vigia.navigation.Route
import github.com.pinmarigor.vigia.ui.screens.share_post.SharedData
import github.com.pinmarigor.vigia.data.model.Post
import github.com.pinmarigor.vigia.data.model.PostType
import github.com.pinmarigor.vigia.ui.theme.GradientStart
import java.time.LocalDateTime

class Postt (
    val id: Int,
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
fun Post (posts: Postt, navController: NavController, post: Post) {
    // valores de post
    val typePost = post.type
    val description: String = post.description
    val latitude: Double? = post.latitude
    val longitude: Double? = post.longitude
    val createdAt: LocalDateTime = post.createdAt

    // variáveis de informações gráficas
    var modifier: Modifier
    var imageVector : ImageVector
    var descIcon: String
    var backgroundColorIcon: Color
    var type: String
    var desc: String
    var location: String = post.locationName
    var time: String

    // variáveis de interface
    var countLike by remember { mutableStateOf(0) }
    var countComment by remember { mutableStateOf(0) }
    var like by remember { mutableStateOf(false) }

    if (typePost == PostType.ATIVIDADE_SUSPEITA) {
        modifier = Modifier.background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp)).padding(12.dp)
        imageVector = Icons.Default.Warning
        descIcon = "warning"
        backgroundColorIcon = Color(0xFFC7B300)
        type = post.type.name
        desc = post.description
        location = "a descobrir"
        time = post.createdAt.toString()
    } else if (typePost == PostType.ROUBO) {
        modifier = Modifier.background(Color(0xFFB90202), shape = RoundedCornerShape(12.dp)).padding(12.dp)
        imageVector = Icons.Default.Warning
        descIcon = "theft"
        backgroundColorIcon = Color(0xFFD20303)
        type = post.type.name
        desc = post.description
        location = "a descobrir"
        time = post.createdAt.toString()
    } else if (typePost == PostType.ILUMINACAO_RUIM) {
        modifier = Modifier.background(GradientStart, shape = RoundedCornerShape(12.dp)).padding(12.dp)
        imageVector = Icons.Default.Lightbulb
        descIcon = "lighting"
        backgroundColorIcon = Color(0xF30060AB)
        type = post.type.name
        desc = post.description
        location = "a descobrir"
        time = post.createdAt.toString()
    } else if (typePost == PostType.AREA_SEGURA) {
        modifier = Modifier.background(Color(0xE618960B), shape = RoundedCornerShape(12.dp)).padding(12.dp)
        imageVector = Icons.Default.Shield
        descIcon = "safe"
        backgroundColorIcon = Color(0xF700C70A)
        type = post.type.name
        desc = post.description
        location = "a descobrir"
        time = post.createdAt.toString()
    } else {
        modifier = Modifier.background(Color(0xFF606060), shape = RoundedCornerShape(12.dp)).padding(12.dp)
        imageVector = Icons.Default.Shield
        descIcon = "any"
        backgroundColorIcon = Color(0xF7DEDEDE)
        type = post.type.name
        desc = post.description
        location = "a descobrir"
        time = post.createdAt.toString()
    }

    Box(
        modifier = posts.modifier
    ) {
        Row() {
            Box(modifier = Modifier
                .background(posts.backgroundColorIcon, shape = RoundedCornerShape(2.dp))
                .padding(8.dp)
            ) {
                Icon(
                    imageVector = posts.imageVector,
                    contentDescription = posts.description,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column() {
                Text(
                    text = posts.type,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = posts.desc,
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
                        text = posts.location,
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
                        text = posts.time,
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
                            SharedData.selectedPost = posts
                            println("POST SALVO: ${posts.type}")
                            navController.navigate(Route.Comments(countComment))
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