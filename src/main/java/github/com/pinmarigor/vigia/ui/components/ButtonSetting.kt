package github.com.pinmarigor.vigia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ButtonSetting(
    var imageVector: ImageVector,
    var tint: Color,
    var name: String,
    var optional: String = ""
    )

@Composable
fun ButtonSetting(but: List<ButtonSetting>) {
    Column(
        modifier = Modifier
            .background(Color(0xFF051425), shape = RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = Color(0xFF002E59), shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.Start
    ) {
        but.forEachIndexed { index, b ->
            val isLastItem = index == but.size - 1  // Agora o 'index' está disponível

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .then (
                        if (!isLastItem) {
                            Modifier.drawBehind {
                                val strokeWidth = 2.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFF002E59),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                        } else {
                            Modifier
                        }
                    )
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF051425)
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF052A4D), shape = RoundedCornerShape(8.dp))
                            .padding(5.dp)
                    ) {
                        Icon(
                            imageVector = b.imageVector,
                            contentDescription = null,
                            tint = b.tint,
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column() {
                        Text(
                            text = b.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        if(!b.optional.isEmpty()) {
                            Text(
                                text = b.optional,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }
        }
    }
}