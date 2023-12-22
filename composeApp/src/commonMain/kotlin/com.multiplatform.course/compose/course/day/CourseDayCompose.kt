package com.multiplatform.course.compose.course.day

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.item.CourseItemCompose
import kotlinx.datetime.DayOfWeek

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 17:47
 */
abstract class CourseDayCompose(
  val dayOfWeek: DayOfWeek,
  val paddingBottom: Dp
) : IComposePresenter {

  abstract val dayItemStates: List<DayItemState>

  @Stable
  class DayItemState(
    beginLesson: Int,
    period: Int,
    val itemCompose: CourseItemCompose
  ) {
    var beginLesson: Int by mutableIntStateOf(beginLesson)
    var period: Int by mutableIntStateOf(period)
  }

  @Composable
  override fun Compose() {
    Box(modifier = Modifier.fillMaxSize().padding(bottom = paddingBottom)) {
      dayItemStates.forEach { item ->
        Column(modifier = Modifier.fillMaxSize()) {
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
      }
    }
  }
}
