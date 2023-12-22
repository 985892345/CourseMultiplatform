package com.multiplatform.course.compose.course.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 15:35
 */
@Composable
fun CourseTimelineCompose(isShowNowTime: Boolean) {
  Column(modifier = Modifier.fillMaxHeight()) {
    repeat(12) {
      Box(modifier = Modifier.weight(1F).fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = (it + 1).toString(), textAlign = TextAlign.Center, fontSize = 12.sp)
      }
    }
  }
}