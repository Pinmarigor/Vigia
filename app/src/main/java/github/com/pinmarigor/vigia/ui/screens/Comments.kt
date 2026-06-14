package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.components.Circle
import github.com.pinmarigor.vigia.ui.components.Comment
import github.com.pinmarigor.vigia.ui.components.Post
import github.com.pinmarigor.vigia.ui.navigation.Route
import github.com.pinmarigor.vigia.ui.screens.share_post.SharedData
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle

@Composable
fun Comments(navController: NavController, count: Int) {
    val post = SharedData.selectedPost

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBlue,
                        GradientMiddle,
                        DarkBlue
                    )
                )
            )
            .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF3B3B3B), shape = RoundedCornerShape(8.dp))
                    .padding(10.dp)
                    .clickable{
                        navController.navigate(Route.Community)
                    },
                Alignment.TopStart
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    text = "Comentários",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$count comentários",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(Color(0xFF3B3B3B), shape = RoundedCornerShape(8.dp))
                    .padding(10.dp)
                    .clickable{
                        navController.navigate(Route.Community)
                    },
            ) {
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = "Flag",
                    tint = Color.White
                )
            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        post?.let {
            Post(
                post = it,
                navController = navController
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        val com1: Comment = Comment(
            "M",
            "Marina S.",
            "há 5 min",
            "Passei por lá agora há pouco, ainda tem movimento de pessoas suspeitas na esquina.",
        )
        val com2: Comment = Comment(
            "R",
            "Roberto M.",
            "há 18 min",
            "Vi a mesma situação ontem no mesmo horário. Parece que é rotina no local.",
        )
        val com3: Comment = Comment(
            "J",
            "Juliana T.",
            "há 22 min",
            "Confirmado! Minha vizinha acabou de me mandar mensagem falando a mesma coisa. Cuidado pessoal!",
        )
        val com4: Comment = Comment(
            "P",
            "Pedro A.",
            "há 35 min",
            "Sugiro usar a Rua Bela Cintra como alternativa, está bem iluminada e movimentada.",
        )

        val list: List<Comment> = listOf(com1, com2, com3, com4)

        list.forEach { c ->
            Column () {
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Comment(c)
                }
            }
        }
    }
}