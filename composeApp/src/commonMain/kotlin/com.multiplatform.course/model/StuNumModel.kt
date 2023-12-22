package com.multiplatform.course.model

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.multiplatform.course.compose.dialog.ChooseDialog
import com.multiplatform.course.compose.dialog.Dialog
import com.multiplatform.course.compose.toast.toast
import com.multiplatform.course.platform.Preference

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 21:35
 */
object StuNumModel {

  /**
   * 可观察的学号
   */
  var stuNum: String? by mutableStateOf(Preference.getString("stuNum"))
    private set

  /**
   * 显示设置学号的 dialog
   * @param onSetStuNum 点击确认时的回调，返回 false 则说明此时学号不合法，建议业务方此时 toast 原因
   */
  fun showStuNumDialog(onSetStuNum: Dialog.(stuNum: String) -> Boolean = { true }): Dialog {
    val text = mutableStateOf(TextFieldValue(text = stuNum ?: ""))
    return ChooseDialog(
      isTwoBtn = false,
      properties = DialogProperties(
        dismissOnBackPress = stuNum != null,
        dismissOnClickOutside = false,
      ),
      onClickPositionBtn = {
        if (isValidStuNum(text.value.text)) {
          if (onSetStuNum.invoke(this, text.value.text)) {
            Preference.edit { put("stuNum", text.value.text) }
            hide()
            stuNum = text.value.text
          }
        } else {
          toast("学号有误")
        }
      },
    ) {
      ContentCompose(text)
    }.apply { show() }
  }

  @OptIn(ExperimentalMaterialApi::class)
  @Composable
  private fun ContentCompose(text: MutableState<TextFieldValue>) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 60.dp),
      contentAlignment = Alignment.Center
    ) {
      val interactionSource = remember { MutableInteractionSource() }
      BasicTextField(
        modifier = Modifier
          .width(200.dp)
          .height(30.dp)
          .background(Color.Transparent)
          .indicatorLine(
            enabled = true,
            isError = false,
            interactionSource = interactionSource,
            colors = TextFieldDefaults.textFieldColors()
          ),
        value = text.value,
        onValueChange = {
          text.value = it
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Number
        ),
        interactionSource = interactionSource,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp),
        decorationBox = {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (text.value.text.isEmpty()) {
              Text(text = "请输入学号", color = Color.Black)
            }
            it.invoke()
          }
        }
      )
    }
  }

  private fun isValidStuNum(stuNum: String): Boolean {
    return stuNum.startsWith("202") && stuNum.length == 10
  }
}