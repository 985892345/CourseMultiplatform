package com.multiplatform.course.network.api

import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.POST
import com.multiplatform.course.network.bean.StuLessonBean

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/20 21:36
 */
interface CourseApiService {

  @POST("https://be-prod.redrock.cqupt.edu.cn/magipoke-jwzx/kebiao")
  @FormUrlEncoded
  suspend fun getStuLesson(
    @Field("stu_num")
    stuNum: String
  ): StuLessonBean
}