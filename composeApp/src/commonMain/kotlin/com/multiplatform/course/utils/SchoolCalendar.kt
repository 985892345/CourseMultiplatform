package com.multiplatform.course.utils

import com.multiplatform.course.platform.Preference
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus

/**
 *
 * 学校日历的工具类
 *
 * 这里面可以得到开学的第一天
 *
 * 后端并没有单独提供某个接口来获取当前开学周数，每次的开学周数都是从课表接口那里得到的
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/15 14:59
 */
object SchoolCalendar {
  
  /**
   * 得到这学期过去了多少天
   *
   * 返回 null，则说明不知道开学第一天是好久
   *
   * # 注意：存在返回负数的情况！！！
   */
  fun getDayOfTerm(): Int? {
    return getFirstMonDateOfTerm()?.let {
      Today.diffDays(it)
    }
  }
  
  /**
   * 得到当前周数
   *
   * @return 返回 null，则说明不知道开学第一天是好久；返回 0，则表示开学前的一周（因为第一周开学）
   *
   * # 注意：存在返回负数的情况！！！
   * ```
   *     -1      0      1      2       3        4             返回值
   *  ----------------------------------------------------------->
   * -14     -7      0      7      14       21       28       天数差
   * ```
   */
  fun getWeekOfTerm(): Int? {
    val dayOfTerm = getDayOfTerm() ?: return null
    return if (dayOfTerm >= 0) dayOfTerm / 7 + 1 else dayOfTerm / 7
  }
  
  /**
   * 得到开学第一周的星期一
   *
   * @return 返回 null，则说明不知道开学第一天是好久
   */
  fun getFirstMonDateOfTerm(): LocalDate? {
    return mFirstMonDate.date
  }
  
  /**
   * 得到开学第一周的星期一的时间戳
   */
  fun getFirstMonDayTimestamp(): Long? {
    return getFirstMonDateOfTerm()
      ?.atStartOfDayIn(TimeZone.currentSystemDefault())
      ?.toEpochMilliseconds()
  }

  /**
   * 更新开学时间
   * @param nowWeek 当前周数，支持负数
   */
  fun updateFirstCalendar(nowWeek: Int) {
    val date = Today.minus(
      (nowWeek - 1) * 7 + Today.dayOfWeek.ordinal,
      DateTimeUnit.DayBased(1)
    )
    if (date == getFirstMonDateOfTerm()) return
    Preference.edit {
      put(FIRST_MON_DATE, date.toEpochDays())
    }
    mFirstMonDate.date = date
  }
  
  private const val FIRST_MON_DATE = "first_toEpochDays"

  private val mFirstMonDate: FirstMonDate = FirstMonDate(
    Preference.getInt(FIRST_MON_DATE, 0)
      .takeIf { it != 0 }
      ?.let { LocalDate.fromEpochDays(it) }
  )

  // 为了解决目前 native 不能修改单例中变量，包了一层
  private data class FirstMonDate(
    var date: LocalDate?,
  )
}