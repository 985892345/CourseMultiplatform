import androidx.compose.ui.window.ComposeUIViewController
import com.g985892345.provider.coursemultiplatform.composeapp.ComposeAppKtProviderInitializer

fun MainViewController() = ComposeUIViewController { App() }

fun tryInitKtProvider() {
  ComposeAppKtProviderInitializer.tryInitKtProvider()
}
