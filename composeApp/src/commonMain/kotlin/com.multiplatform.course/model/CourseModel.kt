package com.multiplatform.course.model

import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import com.multiplatform.course.compose.course.CoursePagerCompose
import com.multiplatform.course.compose.course.item.CourseItemCompose
import com.multiplatform.course.compose.course.layout.CourseLayoutCompose
import com.multiplatform.course.compose.toast.toast
import com.multiplatform.course.network.api.CourseApiService
import com.multiplatform.course.network.bean.StuLessonBean
import com.multiplatform.course.utils.SchoolCalendar
import com.multiplatform.course.utils.parseClassRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DayOfWeek

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 19:26
 */
object CourseModel {

  fun getData(): Flow<CourseData> {
    return snapshotFlow { StuNumModel.stuNum }
      .filterNotNull()
      .map {
        val data = CourseApiService().getStuLesson(it)
        val list = data.data.map { lesson ->
          lesson.week.map { week ->
            createPagerItemState(week, lesson)
          } + createPagerItemState(0, lesson)
        }.flatten()
        SchoolCalendar.updateFirstCalendar(data.nowWeek)
        CourseData(data.nowWeek, list)
      }.catch {
        toast("网络异常")
      }
  }

  data class CourseData(
    val nowWeek: Int,
    val list: List<CoursePagerCompose.PagerItemState>
  ) {
    companion object {
      val Empty = CourseData(SchoolCalendar.getWeekOfTerm() ?: -1, emptyList())
    }
  }

  private fun createPagerItemState(
    week: Int,
    lesson: StuLessonBean.StuLesson
  ): CoursePagerCompose.PagerItemState {
    return CoursePagerCompose.PagerItemState(
      week = week,
      layoutItem = CourseLayoutCompose.LayoutItem(
        dayOfWeek = DayOfWeek(lesson.hashDay + 1),
        beginLesson = lesson.beginLesson,
        period = lesson.period,
        itemCompose = CourseItemCompose(
          title = lesson.course,
          content = parseClassRoom(lesson.classroom),
          titleColor = when (lesson.beginLesson) {
            in 1..4 -> Color(0xFFFF8015)
            in 5..8 -> Color(0xFFFF6262)
            in 9..12 -> Color(0xFF4066EA)
            else -> Color.Black
          },
          contentColor = when (lesson.beginLesson) {
            in 1..4 -> Color(0xFFFF8015)
            in 5..8 -> Color(0xFFFF6262)
            in 9..12 -> Color(0xFF4066EA)
            else -> Color.Black
          },
          itemColor = when (lesson.beginLesson) {
            in 1..4 -> Color(0xFFF9E7D8)
            in 5..8 -> Color(0xFFF9E3E4)
            in 9..12 -> Color(0xFFDDE3F8)
            else -> Color.Black
          },
        )
      )
    )
  }
}