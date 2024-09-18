import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun StopwatchUI() {
    val stopwatch = remember { Stopwatch() }
    var elapsedTime by remember { mutableStateOf(0L) }

    LaunchedEffect(stopwatch.isRunning) {
        while (stopwatch.isRunning) {
            elapsedTime = stopwatch.getElapsedTime()
            delay(100L) // UI 업데이트 주기
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            formatTime(elapsedTime), fontSize = 48.sp, modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (!stopwatch.isRunning) {
                Button(onClick = {
                    stopwatch.reset()
                    elapsedTime = 0L
                }) {
                    Text("Reset")
                }
            }

            Button(onClick = {
                if (stopwatch.isRunning) {
                    stopwatch.stop()
                } else {
                    stopwatch.start()
                }
            }) {
                Text(text = if (stopwatch.isRunning) "Stop" else "Start")
            }
        }
    }
}

fun formatTime(elapsedMillis: Long): String {
    val seconds = (elapsedMillis / 1000) % 60
    val minutes = (elapsedMillis / 60000) % 60
    val hours = (elapsedMillis / 3600000)
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
