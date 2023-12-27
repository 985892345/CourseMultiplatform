package com.multiplatform.course.compose.course.combine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.layout.CourseLayoutCompose
import com.multiplatform.course.compose.course.timeline.CourseTimelineCompose
import com.multiplatform.course.compose.course.week.CourseWeekCompose
import com.multiplatform.course.utils.Today
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 15:46
 */
abstract class CourseCombineCompose(
  val week: Int,
  val paddingBottom: Dp = 0.dp,
) : IComposePresenter {

  abstract val nowWeek: Int

  abstract val layoutItems: List<CourseLayoutCompose.LayoutItem>

  private val monDate by derivedStateOf(structuralEqualityPolicy()) {
    // 获取 week 页对应的星期一日期，如果 week = 0，则说明是整学期，返回 null
    if (week <= 0) null else Today
      .minus(Today.dayOfWeek.ordinal, DateTimeUnit.DayBased(1))
      .minus((nowWeek - week) * 7, DateTimeUnit.DayBased(1))
  }

  private val isNowWeek by derivedStateOf(structuralEqualityPolicy()) {
    week == nowWeek
  }

  val layoutCompose = object : CourseLayoutCompose(
    paddingBottom = paddingBottom,
  ) {
    override val isNowWeek: Boolean
      get() = this@CourseCombineCompose.isNowWeek
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
