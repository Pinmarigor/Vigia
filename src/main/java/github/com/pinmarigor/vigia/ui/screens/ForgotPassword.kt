package github.com.pinmarigor.vigia.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.ui.theme.CyanBlue
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientEnd
import github.com.pinmarigor.vigia.ui.theme.LightBLue

@Composable
fun ForgotPassword(navController: NavController) {
    var email by rememberSaveable{ mutableStateOf(value = "") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBlue,
                        Color(0xFF132238),
                        DarkBlue
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 12.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        color = Color(0xFF00BFFF),
                        shape = RoundedCornerShape(24.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Shield Icon",

                    tint = Color.White,

                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Esqueceu a senha?",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sem problemas! Digite seu email e enviaremos instruções para recuperar sua conta.",
                color = GradientEnd,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(420.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkBlue,
                ),
                border = BorderStroke(1.dp, CyanBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Email cadastrado",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text(text = "E-mail") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color.LightGray
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.LightGray,
                            unfocusedTextColor = Color.LightGray,
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        enabled = email.isNotEmpty(),
                        onClick = {
                            navController.navigate("forgot_password_cod/$email")
                            Toast.makeText(
                                context,
                                "Email de recuperação enviado",
                                Toast.LENGTH_SHORT

                            ).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        shape = RoundedCornerShape(16.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightBLue
                        ),
                    ) {

                        Text(
                            text = "Enviar instruções",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Você receberá um email com um link seguro para redefinir sua senha",
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}