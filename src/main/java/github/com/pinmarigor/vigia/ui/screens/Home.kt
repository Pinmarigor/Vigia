package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle

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
            .padding(20.dp)
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
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x9E327A32),
                                    Color(0xAB275D27),
                                    Color(0xBE214D21)
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
                        navController.navigate("map_screen")
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
        }
    }
}