package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.components.Contacts
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.ui.theme.LightBLue

@Composable
fun Contacts(navController: NavController) {
    Column(
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
        Text(
            text = "Contatos Confiáveis",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Pessoas que podem monitorar sua segurança",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xC1007BFF), shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color(0xFF0088FF), shape = RoundedCornerShape(12.dp))
                .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically

        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xED007BFF), shape = RoundedCornerShape(12.dp))
                    .padding(15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Shield Icon",
                    tint = Color(0xFF00D1FF),
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    text = "Rede de Proteção Ativa",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "4 contatos podem ser acionados em emergências",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        val mother: Contacts = Contacts(
            "M",
            "Maria Silva",
            "Mãe",
            "(11) 99999-1111",
            "maria@email.com"
        );
        val father: Contacts = Contacts(
            "C",
            "Carlos Lima",
            "Pai",
            "(11) 99999-4444",
            "carlos@email.com"
        );
        val brother: Contacts = Contacts(
            "P",
            "Pedro Santos",
            "Irmão",
            "(11) 99999-2222",
            "pedro@email.com"
        );
        val friend: Contacts = Contacts(
            "A",
            "Ana Costa",
            "Amiga",
            "(11) 99999-3333",
            "ana@email.com"
        );
        Contacts(mother)
        Spacer(modifier = Modifier.height(20.dp))
        Contacts(father)
        Spacer(modifier = Modifier.height(20.dp))
        Contacts(brother)
        Spacer(modifier = Modifier.height(20.dp))
        Contacts(friend)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBLue
            )
        ) {
            Row() {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon",
                    tint = Color.White,

                )
                Text(
                    text = "+ Adicionar Contato",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

        }
    }
}