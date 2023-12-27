import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        // init KtProvider
        ComposeAppKtProviderInitializer.shared.tryInitKtProvider()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
