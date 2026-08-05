package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.components.Comment
import github.com.pinmarigor.vigia.ui.components.Post
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.viewmodel.AuthState
import github.com.pinmarigor.vigia.viewmodel.AuthViewModel
import github.com.pinmarigor.vigia.viewmodel.PostViewModel

@Composable
fun Comments(
    navController: NavController,
    postId: String,
    count: Int,
    postViewModel: PostViewModel,
    authViewModel: AuthViewModel
) {
    val post by postViewModel.selectedPostState.collectAsState()
    val currentUserId = (authViewModel.authState as? AuthState.Authenticated)?.uid ?: ""

    LaunchedEffect(postId) {
        postViewModel.getPostById(postId)
    }

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
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF3B3B3B), shape = RoundedCornerShape(8.dp))
                    .padding(10.dp)
                    .clickable{
                        navController.popBackStack()
                    },
                Alignment.TopStart
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Post e Comentários",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
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
                    .padding(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = "Flag",
                    tint = Color.White
                )
            }

        }
        
        Spacer(modifier = Modifier.height(24.dp))

        if (post == null) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Cyan)
            }
        } else {
            post?.let {
                Post(
                    navController = navController,
                    post = it,
                    currentUserId = currentUserId,
                    onLikeClick = { postViewModel.toggleLike(it.uid, currentUserId) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Comentários",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Comentários mockados conforme solicitado na Etapa 5 (preparação)
        val com1 = Comment("M", "Marina S.", "há 5 min", "Passei por lá agora há pouco, ainda tem movimento.")
        val com2 = Comment("R", "Roberto M.", "há 18 min", "Vi a mesma situação ontem no mesmo horário.")
        val com3 = Comment("J", "Juliana T.", "há 22 min", "Confirmado! Cuidado pessoal!")
        val com4 = Comment("P", "Pedro A.", "há 35 min", "Sugiro usar a Rua Bela Cintra como alternativa.")

        val list = listOf(com1, com2, com3, com4)

        list.forEach { c ->
            Spacer(modifier = Modifier.height(16.dp))
            Comment(c)
        }
    }
}
