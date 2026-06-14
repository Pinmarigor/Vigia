package github.com.pinmarigor.vigia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Comment(
    val nameCircle: String,
    val name: String,
    val time: String,
    val comment: String,
)

@Composable
fun Comment(c: Comment) {
    var commentLike by remember { mutableStateOf(0) }
    var like by remember { mutableStateOf(false) }



    Row() {
        Circle(c.nameCircle)
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Column(
                modifier = Modifier
                    .background(Color(0xFF002B57), shape = RoundedCornerShape(12.dp))
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = c.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "time",
                            tint = Color.LightGray,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = c.time,
                            color = Color.LightGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = c.comment,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clickable{
                            like = !like

                            if(like) {
                                commentLike++
                            } else {
                                commentLike--
                            }
                        },
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = "ThumbUp",
                            tint = Color(0xFF74DCFF)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = commentLike.toString(),
                            color = Color(0xFF74DCFF),
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Responder",
                    color = Color(0xFF011BB0),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(15.dp))
                Row() {
                    Icon(
                        imageVector = Icons.Filled.ChatBubbleOutline,
                        contentDescription = "ChatBubbleOutline",
                        tint = Color(0xFF011BB0)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Respostas",
                        color = Color(0xFF011BB0),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

}