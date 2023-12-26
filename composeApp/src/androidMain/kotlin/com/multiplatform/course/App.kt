package com.multiplatform.course

import android.app.Application
import com.g985892345.provider.coursemultiplatform.composeapp.ComposeAppKtProviderInitializer

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/27 00:18
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    ComposeAppKtProviderInitializer.tryInitKtProvider()
  }
}