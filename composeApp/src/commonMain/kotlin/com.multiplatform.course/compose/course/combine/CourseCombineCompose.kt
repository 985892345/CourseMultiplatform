package com.multiplatform.course.compose.course.combine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.layout.CourseLayoutCompose
import com.multiplatform.course.compose.course.timeline.CourseTimelineCompose
import com.multiplatform.course.compose.course.week.CourseWeekCompose
import com.multiplatform.course.utils.Today
import com.multiplatform.course.utils.diffDays
import kotlinx.datetime.LocalDate

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 15:46
 */
abstract class CourseCombineCompose(
  val monDate: LocalDate?,
  val paddingBottom: Dp = 0.dp,
) : IComposePresenter {

  abstract val layoutItems: List<CourseLayoutCompose.LayoutItem>

  private val isNowWeek = if (monDate == null) true else Today.diffDays(monDate) in 0..6

  val layoutCompose = object : CourseLayoutCompose(
    isNowWeek = isNowWeek,
    paddingBottom = paddingBottom,
  ) {
    override val layoutItems: List<LayoutItem>
      get() = this@CourseCombineCompose.layoutItems
  }

  @Composable
  override fun Compose() {
    Column(modifier = Modifier.fillMaxSize().padding(start = 4.dp, end = 8.dp)) {
      val monthWeight = 0.8F
      Box(modifier = Modifier.height(52.dp)) {
        CourseWeekCompose(monDate = monDate, monthWeight = monthWeight)
      }
      Box(modifier = Modifier.weight(12F)) {
        Row(modifier = Modifier.fillMaxSize()) {
          Box(modifier = Modifier.weight(monthWeight).padding(bottom = paddingBottom)) {
            CourseTimelineCompose(
              isShowNowTime = isNowWeek
            )
          }
          Box(modifier = Modifier.weight(7F)) {
            layoutCompose.Compose()
          }
        }
      }
    }
  }
}
