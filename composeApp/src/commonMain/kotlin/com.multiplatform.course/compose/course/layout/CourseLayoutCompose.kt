package com.multiplatform.course.compose.course.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.item.CourseItemCompose
import com.multiplatform.course.utils.Today
import kotlinx.datetime.DayOfWeek

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 15:33
 */
abstract class CourseLayoutCompose(
  val isNowWeek: Boolean,
  val paddingBottom: Dp,
) : IComposePresenter {

  /**
   * 每天对应的数据
   */
  abstract val layoutItems: List<LayoutItem>

  @Stable
  class LayoutItem(
    dayOfWeek: DayOfWeek,
    beginLesson: Int,
    period: Int,
    itemCompose: CourseItemCompose,
  ) {
    var dayOfWeek by mutableStateOf(dayOfWeek)
    var beginLesson by mutableIntStateOf(beginLesson)
    var period by mutableIntStateOf(period)
    var itemCompose by mutableStateOf(itemCompose)
  }

  @Composable
  override fun Compose() {
    Box(modifier = Modifier.fillMaxSize()) {
      if (isNowWeek) {
        TodayBackgroundCompose()
      }
      layoutItems.forEach {
        LayoutItemCompose(it)
      }
    }
  }

  @Composable
  private fun TodayBackgroundCompose() {
    Row {
      val startWeight = Today.dayOfWeek.ordinal
      if (startWeight > 0) {
        Spacer(modifier = Modifier.weight(startWeight.toFloat()))
      }
      Spacer(
        modifier = Modifier.weight(1F)
          .fillMaxHeight()
          .background(color = Color(0x93E8F0FC))
      )
      val endWeight = 6 - Today.dayOfWeek.ordinal
      if (endWeight > 0) {
        Spacer(modifier = Modifier.weight(endWeight.toFloat()))
      }
    }
  }

  @Composable
  private fun LayoutItemCompose(item: LayoutItem) {
    Row(
      modifier = Modifier.padding(bottom = paddingBottom)
        .offset(x = item.itemCompose.offsetX, y = item.itemCompose.offsetY)
    ) {
      val startWeight = item.dayOfWeek.ordinal
      if (startWeight > 0) {
        Spacer(modifier = Modifier.weight(startWeight.toFloat()))
      }
      Column(modifier = Modifier.weight(1F)) {
        val topWeight = item.beginLesson - 1F
        if (topWeight > 0F) {
          Spacer(modifier = Modifier.weight(topWeight))
        }
        Box(modifier = Modifier.weight(item.period.toFloat())) {
          item.itemCompose.Compose()
        }
        val bottomWeight = 13F - item.beginLesson - item.period
        if (bottomWeight > 0) {
          Spacer(modifier = Modifier.weight(bottomWeight))
        }
      }
      val endWeight = 6 - item.dayOfWeek.ordinal
      if (endWeight > 0) {
        Spacer(modifier = Modifier.weight(endWeight.toFloat()))
      }
    }
  }
}

