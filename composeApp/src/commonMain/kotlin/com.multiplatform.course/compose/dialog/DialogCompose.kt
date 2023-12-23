package com.multiplatform.course.compose.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
  open val properties: DialogProperties = DialogProperties(),
  open val onDismissRequest: Dialog.() -> Unit = { hide() }
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

  companion object : Dialog() {
    @Composable
    override fun Content() = Unit
  }
}

private var AppDialogEnable by mutableStateOf(false)

private var AppDialogState: Dialog by mutableStateOf(Dialog)

@Composable
fun DialogCompose() {
  if (AppDialogEnable) {
    Dialog(
      properties = AppDialogState.properties,
      onDismissRequest = {
        AppDialogState.onDismissRequest.invoke(AppDialogState)
      }
    ) {
      AppDialogState.Content()
    }
  }
}