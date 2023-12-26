package com.multiplatform.course.network.api

import com.multiplatform.course.network.AppHttpClient
import com.multiplatform.course.network.bean.StuLessonBean
import io.github.seiko.ktorfit.annotation.generator.GenerateApi
import io.github.seiko.ktorfit.annotation.http.Field
import io.github.seiko.ktorfit.annotation.http.FormUrlEncoded
import io.github.seiko.ktorfit.annotation.http.POST
import io.ktor.client.HttpClient

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/20 21:36
 */
@GenerateApi
expect class CourseApiService(client: HttpClient = AppHttpClient) {

  @POST("https://be-prod.redrock.cqupt.edu.cn/magipoke-jwzx/kebiao")
  @FormUrlEncoded
  suspend fun getStuLesson(
    @Field("stu_num")
    stuNum: String
  ): StuLessonBean
}

