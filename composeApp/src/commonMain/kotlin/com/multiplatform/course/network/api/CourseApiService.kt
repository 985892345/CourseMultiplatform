package com.multiplatform.course.network.api

import com.multiplatform.course.network.Network
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
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class CourseApiService(client: HttpClient = Network.client) {

  @POST("https://be-prod.redrock.cqupt.edu.cn/magipoke-jwzx/kebiao")
  @FormUrlEncoded
  suspend fun getStuLesson(
    @Field("stu_num")
    stuNum: String
  ): StuLessonBean
}

