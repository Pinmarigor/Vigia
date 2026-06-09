package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.components.Post
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.ui.theme.GradientStart


@Composable
fun Community(navController: NavController) {
    Box(
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
            .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column() {
            Text(
                text = "Comunidade",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Feed colaborativo de ocorrências",
                color = Color.LightGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row{
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filtro",
                    tint = Color.LightGray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Filtros",
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val filters = listOf("Todos", "Suspeitos", "Roubos", "Iluminação", "Seguros")
                items(filters) { f ->
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1E3A5F),
                            contentColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = f,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))


            val warning: Post = Post(
                Modifier.background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Warning,
                "Alerta",
                Color(0xFFC7B300),
                "Atividade Suspeita",
                "Grupo de pessoas em atitude suspeita próximo ao banco",
                "Av. Paulista, 1500",
                "há 15 min"
            )
            val alert: Post = Post(
                Modifier.background(Color(0xFFB90202), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Warning,
                "Alerta",
                Color(0xFFD20303),
                "Roubo Reportado",
                "Assalto a pedestre relatado por testemunha",
                "Rua Augusta, 800",
                "há 45 min"
            )
            val lighting: Post = Post(
                Modifier.background(GradientStart, shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Filled.Lightbulb,
                "lighting",
                Color(0xF30060AB),
                "Iluminação Precária",
                "Poste queimado, área muito escura à noite",
                "Rua da Consolação, 2000",
                "há 2 horas"
            )
            val safeArea: Post = Post(
                Modifier.background(Color(0xE618960B), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Shield,
                "safe",
                Color(0xF700C70A),
                "Área Segura",
                "Presença de segurança, local bem iluminado",
                "Parque Ibirapuera",
                "há 3 horas"
            )
            val warnings: Post = Post(
                Modifier.background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Warning,
                "warning",
                Color(0xFFC7B300),
                "Atividade Suspeita",
                "Veículo parado há muito tempo sem ocupantes visíveis",
                "Rua Oscar Freire",
                "há 4 horas"
            )

            Post(warning)
            Spacer(modifier = Modifier.height(30.dp))
            Post(alert)
            Spacer(modifier = Modifier.height(30.dp))
            Post(lighting)
            Spacer(modifier = Modifier.height(30.dp))
            Post(safeArea)
            Spacer(modifier = Modifier.height(30.dp))
            Post(warnings)
            Spacer(modifier = Modifier.height(30.dp))


        }
    }
}