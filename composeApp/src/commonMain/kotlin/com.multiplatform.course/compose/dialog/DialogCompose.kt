package com.multiplatform.course.compose.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 10:41
 */
@Stable
abstract class Dialog(
  open val properties: DialogProperties
) {

  @Composable
  abstract fun Content()

  open fun show(): Boolean {
    if (!AppDialogEnable) {
      AppDialogState = this
      AppDialogEnable = true
      return true
    }
    return false
  }

  open fun hide() {
    if (AppDialogEnable) {
      AppDialogEnable = false
    }
  }

  companion object : Dialog(DialogProperties()) {
    @Composable
    override fun Content() = Unit
  }
}

private var AppDialogEnable by mutableStateOf(false)

private var AppDialogState: Dialog by mutableStateOf(Dialog)

@Composable
fun DialogCompose() {
  if (AppDialogEnable) {
    Dialog(onDismissRequest = { AppDialogEnable = false }, properties = AppDialogState.properties) {
      AppDialogState.Content()
    }
  }
}