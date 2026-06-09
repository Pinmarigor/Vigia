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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import github.com.pinmarigor.vigia.ui.components.Warning
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.ui.theme.GradientStart

@Composable
fun Warnings() {
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
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alertas",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Box (
                    modifier = Modifier.background(Color(0xFF690400), shape = RoundedCornerShape(12.dp)).padding(12.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alerts",
                        tint = Color(0xFFFF2014)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Notificações de segurança",
                color = Color.LightGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(30.dp))

            val alertSec: Warning = Warning(
                Modifier.background(Color(0xFFB90202), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Warning,
                "Alerta",
                Color(0xFFD20303),
                Color(0xBFFF5353),
                "Área de Alto Risco",
                "Roubo reportado na região há Pouco tempo",
                "Av. Paulista, 1500",
                "há 20 min"
            )
            val alertLight: Warning = Warning(
                Modifier.background(Color(0x92CBB700), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Warning,
                "Alerta",
                Color(0xFFC7B300),
                Color(0xDAFFEA68),
                "Iluminação Precária",
                "Poste queimado relatado pela comunidade",
                "Rua da Consolação",
                "há 1 hora"
            )
            val alertAlternativeRoute: Warning = Warning(
                Modifier.background(GradientStart, shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.Info,
                "Alerta",
                Color(0xF30060AB),
                Color(0xFF34A3FF),
                "Rota Alternativa",
                "Nova rota 15% mais segura disponível",
                "Centro",
                "há 3 horas"
            )
            val alertSafe: Warning = Warning(
                Modifier.background(Color(0xE618960B), shape = RoundedCornerShape(12.dp)).padding(12.dp),
                Icons.Default.CheckCircle,
                "Alerta",
                Color(0xF700C70A),
                Color(0xFF6DFF75),
                "Trajeto Concluído",
                "Você chegou com segurança ao destino",
                "Shopping Iguatemi",
                "há 5 horas"
            )

            Warning(alertSec)
            Spacer(modifier = Modifier.height(30.dp))
            Warning(alertLight)
            Spacer(modifier = Modifier.height(30.dp))
            Warning(alertAlternativeRoute)
            Spacer(modifier = Modifier.height(30.dp))
            Warning(alertSafe)
            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}