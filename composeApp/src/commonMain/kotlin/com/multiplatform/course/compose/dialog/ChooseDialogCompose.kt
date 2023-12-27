package com.multiplatform.course.compose.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/21 21:06
 */
data class ChooseDialog(
  val width: Dp = 300.dp,
  val height: Dp = 180.dp,
  val isTwoBtn: Boolean = true,
  val btnWidth: Dp = 100.dp,
  val btnHeight: Dp = 38.dp,
  val positiveBtnText: String = "确定",
  val negativeBtnText: String = "取消",
  override val properties: DialogProperties = DialogProperties(),
  override val onDismissRequest: Dialog.() -> Unit = { hide() },
  val onClickPositionBtn: ChooseDialog.() -> Unit = { hide() },
  val onClickNegativeBtn: ChooseDialog.() -> Unit = { hide() },
  val content: @Composable () -> Unit,
) : Dialog(properties) {

  @Composable
  override fun Content() {
    Card(
      modifier = Modifier
        .width(width)
        .height(height),
      shape = RoundedCornerShape(16.dp),
      contentColor = Color.White,
    ) {
      Column(
        modifier = Modifier.fillMaxSize(),
      ) {
        Box(
          modifier = Modifier
            .weight(1F)
            .fillMaxWidth()
        ) {
          content.invoke()
        }
        if (isTwoBtn) {
          TwoBtnCompose()
        } else {
          OneBtnCompose()
        }
      }
    }
  }


  @Composable
  private fun TwoBtnCompose() {
    Row(modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth()) {
      Spacer(modifier = Modifier.weight(1F))
      Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
      ) {
        NegativeBtnCompose()
      }
      Spacer(modifier = Modifier.weight(0.8F))
      Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
      ) {
        PositiveBtnCompose()
      }
      Spacer(modifier = Modifier.weight(1F))
    }
  }

  @Composable
  private fun OneBtnCompose() {
    Box(
      modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth(),
      contentAlignment = Alignment.BottomCenter
    ) {
      PositiveBtnCompose()
    }
  }

  @Composable
  private fun PositiveBtnCompose() {
    Card(
      modifier = Modifier
        .width(btnWidth)
        .height(btnHeight),
      backgroundColor = Color(0xFF4A44E4),
      shape = RoundedCornerShape(26.dp),
    ) {
      Box(
        modifier = Modifier.clickable(onClick = { onClickPositionBtn.invoke(this) }),
        contentAlignment = Alignment.Center
      ) {
        Text(text = positiveBtnText)
      }
    }
  }

  @Composable
  private fun NegativeBtnCompose() {
    Card(
      modifier = Modifier
        .width(btnWidth)
        .height(btnHeight),
      backgroundColor = Color(0xFFC3D4EE),
      shape = RoundedCornerShape(26.dp),
    ) {
      Box(
        modifier = Modifier.clickable(onClick = { onClickNegativeBtn.invoke(this) }),
        contentAlignment = Alignment.Center
      ) {
        Text(text = negativeBtnText, textAlign = TextAlign.Center)
      }
    }
  }
}