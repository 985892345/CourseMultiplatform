import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        // 初始化路由框架 KtProvider
        MainViewControllerKt.tryInitKtProvider()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}