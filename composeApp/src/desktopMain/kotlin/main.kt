import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "CourseMultiplatform",
    state = WindowState(width = 390.dp, height = 800.dp)
  ) {
    App()
  }
}

@Preview
@Composable
fun AppDesktopPreview() {
  App()
}