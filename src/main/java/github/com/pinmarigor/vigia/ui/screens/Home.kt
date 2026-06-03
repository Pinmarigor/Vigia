package github.com.pinmarigor.vigia.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import github.com.pinmarigor.vigia.ui.components.UnpcomingNotices
import github.com.pinmarigor.vigia.ui.navigation.Route
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.ui.theme.GradientStart
import github.com.pinmarigor.vigia.ui.theme.LightBLue

@Composable
fun Home(navController: NavController) {
    val area: String = ""
    val region: String = ""
    val index: String = ""
    val neighborhood: String = ""
    val score: String = ""
    val update: String = ""

    val recife = LatLng(-8.0476, -34.8770)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recife, 13f)
    }

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
            .padding(top = 0.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FloatingActionButton(
            onClick = {},
            containerColor = Color.Red,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Sos,
                contentDescription = "SOS",
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            color = Color(0xFF00BFFF),
                            shape = RoundedCornerShape(15.dp)
                        ),

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Shield Icon",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    text = "Vigia",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(30.dp, 10.dp)
                )
            }
            Text(
                text = "Monitoramento ativo",
                fontSize = 16.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xDC1E4D1E),
                                    Color(0xD8193F19),
                                    Color(0xEB183B18)
                                )
                            )
                        )
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Área $area",
                        color = Color(0xFF5CE65C),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = region,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = index,
                            color = Color(0xFF5CE65C),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = neighborhood,
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "Índice",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            /* Icon(
                                imageVector = Icons.Default.,
                                contentDescription = "Shield Icon",
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            ) */
                            Text(
                                text = score,
                                color = Color(0xFF5CE65C),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(10.dp, 0.dp)
                            )
                        }
                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = "Shield Icon",
                                tint = Color.LightGray,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = update,
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(10.dp, 0.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
            ) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    cameraPositionState = cameraPositionState
                )
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Route.MapScreen)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = "Expandir mapa"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        LightBLue,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Iniciar trajeto monitorado",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color(0xFF1A2434),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "comunidade",
                            tint = Color(0xFF2D5785)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Comunidade",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Novos relatos",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.2f))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color(0xFF1A2434),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Alertas",
                            tint = Color.Yellow
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Alertas",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Não lidos",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Avisos Próximos",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(30.dp))

            val modifierYellow = Modifier
                .background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
            val imageVectorYellow = Icons.Default.Warning
            val descriptionYellow = "Alerta"

            val modifierGreen = Modifier
                .background(Color(0x6A209120), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
            val imageVectorGreen = Icons.Default.Shield
            val descriptionGreen = "Área Segura"

            UnpcomingNotices("Iluminação Precária", "Rua da consolação, 500 m", "Há 15 min", modifierYellow, imageVectorYellow, descriptionYellow, Color.Yellow)
            Spacer(modifier = Modifier.height(30.dp))
            UnpcomingNotices("Área Verificada Segura", "Av. Paulista, 300m", "há 1h", modifierGreen, imageVectorGreen, descriptionGreen, Color(0xFF209120))
            Spacer(modifier = Modifier.height(30.dp))


            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = "Contatos confiáveis",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Ver todos",
                    color = GradientStart,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(30.dp))


            val backgroundColor = Color(0xFF1A1F29)
            val avatarColor = Color(0xFF007AFF)
            val buttonBackgroundColor = Color(0xFF1E3A5F)
            val buttonTextColor = Color(0xFF66A3FF)
            val subtitleColor = Color(0xFF8B95A5)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy((-12).dp)
                ) {
                    val initials = listOf("MC", "PS", "AC")
                    initials.forEach { initial ->
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(avatarColor)
                                // Borda da mesma cor do fundo para criar o efeito de "corte" entre eles
                                .border(width = 2.dp, color = backgroundColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = initial,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "3 contatos ativos",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Podem monitorar seus trajetos",
                        color = subtitleColor,
                        fontSize = 14.sp
                    )
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = buttonTextColor
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Gerenciar",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}