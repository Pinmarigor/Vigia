package github.com.pinmarigor.vigia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import github.com.pinmarigor.vigia.ui.screens.Login
import github.com.pinmarigor.vigia.ui.theme.VigiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VigiaTheme {
                Surface {
                    Login()
                }
            }
        }
    }
}