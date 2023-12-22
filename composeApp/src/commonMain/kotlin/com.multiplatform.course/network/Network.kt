package com.multiplatform.course.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Ktorfit 在调用 [Ktorfit.create] 函数时进行 ir 插桩，根据当时的方法泛型找到实现类，
 * 然而因为泛型在编译期不会传递，所以无法代理 [Ktorfit.create] 方法
 *
 * @author 985892345
 * @date 2023/12/22 19:27
 */

val Network by lazy {
  Ktorfit.Builder().build {
    httpClient(HttpClient {
      install(ContentNegotiation) {
        json(Json {
          isLenient = true
          ignoreUnknownKeys = true
        })
      }
    })
  }
}


