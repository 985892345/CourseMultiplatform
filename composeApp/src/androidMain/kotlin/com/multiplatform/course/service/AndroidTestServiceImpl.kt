package com.multiplatform.course.service

import ITestService
import com.g985892345.provider.api.annotation.ImplProvider

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/27 12:13
 */
@ImplProvider(clazz = ITestService::class, name = "android")
class AndroidTestServiceImpl : ITestService {
  override fun test(): String {
    return "Android"
  }
}