package com.multiplatform.course.platform

import com.g985892345.android.extensions.android.processLifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/22 19:06
 */

private var LogJob: Job? = null

actual fun log(msg: CharSequence) {
  android.util.Log.d("ggg", "(${Exception().stackTrace[1].run { "$fileName:$lineNumber" }}) -> " +
      msg)
  LogJob?.cancel()
  LogJob = processLifecycleScope.launch {
    delay(500)
    // 打印分割线
    android.util.Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
        "=======================================================================================")
  }
}