package com.multiplatform.course.service

import ITestService
import com.g985892345.provider.api.annotation.ImplProvider

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/27 12:16
 */
@ImplProvider(clazz = ITestService::class, name = "desktop")
class DesktopTestServiceImpl : ITestService {
  override fun test(): String {
    return "Desktop"
  }
}