package github.com.pinmarigor.vigia.ui.screens.login

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.navigation.Route
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.FormColor
import github.com.pinmarigor.vigia.ui.theme.LightBLue

@Composable
fun RegisterScreen(
    navController: NavController,
    onRegister: (String, String, String, String) -> Unit,
    errorMessage: String?,
    onErrorConsumed: () -> Unit
) {
    var name by rememberSaveable() { mutableStateOf(value="") }
    var email by rememberSaveable() { mutableStateOf(value = "") }
    var telephone by rememberSaveable() { mutableStateOf(value = "") }
    var password by rememberSaveable() { mutableStateOf(value = "") }
    var repeatPassword by rememberSaveable() { mutableStateOf(value="") }
    var acceptedTerms by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            onErrorConsumed()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBlue,
                        Color(0xFF132238),
                        DarkBlue
                    )
                )
            )
            .padding(bottom = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 80.dp),
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
                text = "Vigia",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sua segurança começa aqui",
                color = Color.Cyan,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(50.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(450.dp),
                shape = RoundedCornerShape(30.dp),

                colors = CardDefaults.cardColors(containerColor = FormColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { navController.navigate(Route.LoginScreen) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Entrar",
                                color = Color.DarkGray
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Cadastrar",
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text(text = "Nome") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Name",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text(text = "E-mail") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = telephone,
                        onValueChange = { telephone = it },
                        placeholder = { Text(text = "Telefone (opcional)") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text(text = "Senha") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = "Password",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = repeatPassword,
                        onValueChange = { repeatPassword = it },
                        placeholder = { Text(text = "Repetir Senha") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = "RepeatPassword",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray,

                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = acceptedTerms,
                            onCheckedChange = {
                                acceptedTerms = it
                            },
                            colors = CheckboxDefaults.colors (
                                checkedColor = LightBLue,
                                uncheckedColor = Color.Gray,
                                checkmarkColor = Color.White
                            )
                        )

                        Text(
                            text = "Ao cria a conta, você concorda com nossos Termos de Uso e Política de Privacidade",
                            fontSize = 12.sp
                        )
                    }


                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        enabled =
                            name.isNotEmpty() &&
                            email.isNotEmpty() &&
                            password.isNotEmpty() &&
                            repeatPassword.isNotEmpty() &&
                            acceptedTerms &&
                            password == repeatPassword,
                        onClick = {
                            onRegister(name, email, password, telephone)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightBLue
                        )
                    ) {
                        Text(
                            text = "Cadastrar",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
