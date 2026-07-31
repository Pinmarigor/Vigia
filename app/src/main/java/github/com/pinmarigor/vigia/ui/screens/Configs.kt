package github.com.pinmarigor.vigia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import github.com.pinmarigor.vigia.data.model.User
import github.com.pinmarigor.vigia.ui.components.ButtonSetting
import github.com.pinmarigor.vigia.ui.theme.DarkBlue
import github.com.pinmarigor.vigia.ui.theme.GradientMiddle
import github.com.pinmarigor.vigia.viewmodel.AuthViewModel

@Composable
fun Configs(
    navController: NavController,
    authViewModel: AuthViewModel,
    onSignOut: () -> Unit,
    onDelete: () -> Unit,
) {
    val user: User? = authViewModel.currentUser
    var showDeleteDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(authViewModel.errorMessage) {
        authViewModel.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            authViewModel.consumeError()
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirmar Exclusão") },
            text = { Text("Tem certeza que deseja excluir sua conta? Esta ação é irreversível e todos os seus dados serão removidos.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    }
                ) {
                    Text("Excluir", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent
    ) { padding ->
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
                .padding(padding)
                .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
        Text(
            text = "Configurações",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Gerencie suas preferências",
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
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00D1FF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user?.name?.take(2)?.uppercase() ?: "??",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Column() {
                Text(
                    text = user?.name ?: "Usuário",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = user?.email ?: "",
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .background(Color(0xC181FA81), shape = RoundedCornerShape(16.dp))
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = "Shield Icon",
                        tint = Color(0xFF11FF00),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Plano Premium",
                        color = Color(0xFF11FF00),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Conta",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        val profile: ButtonSetting = ButtonSetting(
            Icons.Default.Person,
            Color(0xFF005CBE),
            "Perfil",
            user?.name ?: ""
        )
        val notifications: ButtonSetting = ButtonSetting(
            Icons.Default.Notifications,
            Color(0xFF00D1FF),
            "Notificações",
            "Ativadas"
        )
        val privacy: ButtonSetting = ButtonSetting(
            Icons.Default.Lock,
            Color(0xFFFF006F),
            "Privacidade"
        )

        val account: List<ButtonSetting> = listOf(profile, notifications, privacy)
        ButtonSetting(account)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Segurança",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        val monitoring: ButtonSetting = ButtonSetting(
            Icons.Default.Shield,
            Color(0xFF52FF6C),
            "Monitoramento Automático",
            "Ligado"
        )
        val sharedLocation: ButtonSetting = ButtonSetting(
            Icons.Default.LocationOn,
            Color(0xFFFFE827),
            "Compartilhar localização",
            "Apenas Contatos"
        )
        val security: List<ButtonSetting> = listOf(monitoring, sharedLocation)
        ButtonSetting(security)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Preferências",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        val theme: ButtonSetting = ButtonSetting(
            Icons.Default.DarkMode,
            Color(0xE43C31E1),
            "Tema Escuro",
            "Ativada"
        )
        val language: ButtonSetting = ButtonSetting(
            Icons.Default.Language,
            Color(0xD3F561BA),
            "Idioma",
            "Português Brasil"
        )
        val preferences: List<ButtonSetting> = listOf(theme, language)
        ButtonSetting(preferences)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Suporte",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        val support: ButtonSetting = ButtonSetting(
            Icons.Filled.Help,
            Color(0xFF0090FF),
            "Ajuda e Suporte",
            "Português Brasil"
        )
        val help: List<ButtonSetting> = listOf(support)
        ButtonSetting(help)
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { showDeleteDialog = true },
            modifier = Modifier
                .height(80.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8A0000)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = "Trash Icon",
                    tint = Color(0xFFFC8585),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Excluir Conta",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFFFC8585)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = onSignOut,
            modifier = Modifier
                .height(80.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8A0000)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Exit Icon",
                    tint = Color(0xFFFC8585),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Sair da Conta",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFFFC8585)
                )
            }
        }
    }
}
}
