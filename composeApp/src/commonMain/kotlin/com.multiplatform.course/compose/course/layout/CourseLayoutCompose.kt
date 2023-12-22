package com.multiplatform.course.compose.course.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.day.CourseDayCompose
import com.multiplatform.course.utils.Today
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 15:33
 */
abstract class CourseLayoutCompose(
  val monDate: LocalDate?,
  val paddingBottom: Dp,
) : IComposePresenter {

  /**
   * 每天对应的数据
   */
  abstract val dayOfWeekWithItemsMap: Map<DayOfWeek, List<CourseDayCompose.DayItemState>>

  /**
   * 0 -> 星期一的数据
   */
  val dayStates: Array<CourseDayComposeImpl> = arrayOf(
    CourseDayComposeImpl(DayOfWeek.MONDAY),
    CourseDayComposeImpl(DayOfWeek.TUESDAY),
    CourseDayComposeImpl(DayOfWeek.WEDNESDAY),
    CourseDayComposeImpl(DayOfWeek.THURSDAY),
    CourseDayComposeImpl(DayOfWeek.FRIDAY),
    CourseDayComposeImpl(DayOfWeek.SATURDAY),
    CourseDayComposeImpl(DayOfWeek.SUNDAY),
  )

  inner class CourseDayComposeImpl(
    dayOfWeek: DayOfWeek,
  ) : CourseDayCompose(dayOfWeek, paddingBottom) {

    override val dayItemStates: List<DayItemState> by derivedStateOf {
      dayOfWeekWithItemsMap[dayOfWeek] ?: emptyList()
    }
  }

  @Composable
  override fun Compose() {
    Box(modifier = Modifier.fillMaxSize()) {
      Row(modifier = Modifier.fillMaxSize()) {
        var date = monDate ?: Today.minus(Today.dayOfWeek.ordinal, DateTimeUnit.DayBased(1))
        dayStates.forEach {
          key(it.dayOfWeek) {
            val isToday = Today == date
            date = date.plus(1, DateTimeUnit.DayBased(1))
            Box(
              modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
                .background(
                  color = if (isToday) Color(0x93E8F0FC) else Color.Unspecified,
                )
            ) {
              it.Compose()
            }
          }
        }
      }
    }
  }
}

