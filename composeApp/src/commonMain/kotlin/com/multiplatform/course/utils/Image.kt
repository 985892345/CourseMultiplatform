package com.multiplatform.course.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/19 16:02
 */

/**
 * 获取图片，在预览时会返回一个纯颜色图片，用于解决预览时无法加载图片的问题
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun painterImage(res: String): Painter {
  return if (LocalInspectionMode.current) {
    ColorPainter(Color.Blue)
  } else painterResource(res)
}