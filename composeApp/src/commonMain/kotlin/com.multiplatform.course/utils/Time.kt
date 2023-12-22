package com.multiplatform.course.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 19:51
 */

val Today: LocalDate by lazy {
  Clock.System.todayIn(TimeZone.currentSystemDefault())
}