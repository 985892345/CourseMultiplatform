package com.multiplatform.course.compose.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 10:14
 */

class Toast(
  val msg: CharSequence,
  val duration: Long,
) {
  companion object {

    fun show(msg: CharSequence): Boolean {
      return Toast(msg, 1500L).show()
    }

    fun showLong(msg: CharSequence): Boolean {
      return Toast(msg, 3000L).show()
    }
  }

  fun show(): Boolean {
    if (!AppToastEnable) {
      AppToastState = this
      AppToastEnable = true
      return true
    }
    return false
  }
}

private var AppToastEnable by mutableStateOf(false)

private var AppToastState: Toast by mutableStateOf(Toast("", 0L))

@Composable
fun ToastCompose() {
  AnimatedVisibility(visible = AppToastEnable, enter = fadeIn(), exit = fadeOut()) {
    Column {
      Spacer(modifier = Modifier.weight(1F))
      Box(modifier = Modifier.weight(7F).fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Card(
          modifier = Modifier.wrapContentSize(),
          shape = RoundedCornerShape(18.dp),
          backgroundColor = Color(0xFF2A4E84)
        ) {
          Box(
            modifier = Modifier.wrapContentSize()
              .padding(horizontal = 30.dp, vertical = 9.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(text = AppToastState.msg.toString(), color = Color.White, fontSize = 14.sp)
          }
        }
      }
    }
  }
  LaunchedEffect(AppToastEnable) {
    if (AppToastEnable) {
      delay(AppToastState.duration)
      AppToastEnable = false
    }
  }
}

fun toast(msg: CharSequence) = Toast.show(msg)