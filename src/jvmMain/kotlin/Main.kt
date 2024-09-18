import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.theme.MyApplicationTheme

@Composable
@Preview
fun App() {
    MyApplicationTheme {
        Surface {
            StopwatchUI()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 400.dp, height = 300.dp),
        title = "Stop watch",
        resizable = true
    ) {
        App()
    }
}
