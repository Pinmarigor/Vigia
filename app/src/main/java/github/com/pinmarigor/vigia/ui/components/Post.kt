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
import androidx.compose.runtime.mutableIntStateOf
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
import github.com.pinmarigor.vigia.data.model.Post
import github.com.pinmarigor.vigia.data.model.PostType
import github.com.pinmarigor.vigia.navigation.Route
import github.com.pinmarigor.vigia.ui.theme.GradientStart
import java.time.format.DateTimeFormatter

@Composable
fun Post(
    navController: NavController,
    post: Post,
    currentUserId: String = "",
    onLikeClick: () -> Unit = {}
) {
    val typePost = post.type

    val modifier: Modifier
    val imageVector: ImageVector
    val descIcon: String
    val backgroundColorIcon: Color

    when (typePost) {
        PostType.ATIVIDADE_SUSPEITA -> {
            modifier = Modifier
                .background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
            imageVector = Icons.Default.Warning
            descIcon = "warning"
            backgroundColorIcon = Color(0xFFC7B300)
        }
        PostType.ROUBO -> {
            modifier = Modifier
                .background(Color(0xFFB90202), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
            imageVector = Icons.Default.Warning
            descIcon = "theft"
            backgroundColorIcon = Color(0xFFD20303)
        }
        PostType.ILUMINACAO_RUIM -> {
            modifier = Modifier
                .background(GradientStart, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
            imageVector = Icons.Default.Lightbulb
            descIcon = "lighting"
            backgroundColorIcon = Color(0xF30060AB)
        }
        PostType.AREA_SEGURA -> {
            modifier = Modifier
                .background(Color(0xE618960B), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
            imageVector = Icons.Default.Shield
            descIcon = "safe"
            backgroundColorIcon = Color(0xF700C70A)
        }
        else -> {
            modifier = Modifier
                .background(Color(0xFF606060), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
            imageVector = Icons.Default.Shield
            descIcon = "any"
            backgroundColorIcon = Color(0xF7DEDEDE)
        }
    }

    val type = post.type.name.replace("_", " ")
    val desc = post.description
    val location = post.locationName
    val timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    val time = post.createdAt.format(timeFormatter)

    // Estado local para feedback instantâneo, inicializado com os dados do post
    var isLikedLocal by remember(post.uid) { mutableStateOf(post.likedBy.contains(currentUserId)) }
    var likeCountLocal by remember(post.uid) { mutableIntStateOf(post.likeCount) }

    Box(modifier = modifier) {
        Row {
            Box(
                modifier = Modifier
                    .background(backgroundColorIcon, shape = RoundedCornerShape(2.dp))
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = descIcon,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = type,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = desc,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "location",
                        tint = Color.LightGray,
                        modifier = Modifier.width(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = location,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AccessTimeFilled,
                        contentDescription = "time",
                        tint = Color.LightGray,
                        modifier = Modifier.width(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = time,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        onClick = {
                            isLikedLocal = !isLikedLocal
                            if (isLikedLocal) likeCountLocal++ else likeCountLocal--
                            onLikeClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00326C)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                            tint = if (isLikedLocal) Color.Cyan else Color(0xFF509AF8)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = likeCountLocal.toString(),
                            color = Color(0xFF509AF8)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            navController.navigate(Route.Comments(post.uid, post.commentsCount))
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
                            text = post.commentsCount.toString(),
                            color = Color(0xFF509AF8)
                        )
                    }
                }
            }
        }
    }
}
