package com.multiplatform.course.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * 在网络请求的类中采取以下写法：
 * ```
 * @GenerateApi
 * expect class CourseApiService(client: HttpClient = Network.client) {
 *
 *   @POST("https://be-prod.redrock.cqupt.edu.cn/magipoke-jwzx/kebiao")
 *   @FormUrlEncoded
 *   suspend fun getStuLesson(
 *     @Field("stu_num")
 *     stuNum: String
 *   ): StuLessonBean
 * }
 * ```
 *
 * 然后调用处直接 new 对象即可
 * ```
 * val api = CourseApiService()
 * ```
 *
 * @author 985892345
 * @date 2023/12/22 19:27
 */

object Network {

  val client = HttpClient {
    install(ContentNegotiation) {
      json(Json {
        isLenient = true
        ignoreUnknownKeys = true
      })
    }
  }
}


