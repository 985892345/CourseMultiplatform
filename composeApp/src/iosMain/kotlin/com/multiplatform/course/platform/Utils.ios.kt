package com.multiplatform.course.platform

import platform.Foundation.NSLog

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 19:06
 */
actual fun log(msg: CharSequence) {
  NSLog(msg.toString())
}