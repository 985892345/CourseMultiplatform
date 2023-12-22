package com.multiplatform.course.platform

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 19:06
 */
actual fun log(msg: CharSequence) {
  println(".(${Exception().stackTrace[1].run { "$fileName:$lineNumber" }}) -> $msg")
}