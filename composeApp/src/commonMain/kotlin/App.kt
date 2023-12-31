import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.g985892345.provider.manager.KtProviderManager
import com.multiplatform.course.compose.course.rememberCoursePagerState
import com.multiplatform.course.compose.dialog.DialogCompose
import com.multiplatform.course.compose.toast.ToastCompose
import com.multiplatform.course.model.CourseModel
import com.multiplatform.course.model.StuNumModel
import com.multiplatform.course.platform.log

@Composable
fun App() {
  MaterialTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      CourseCompose()
      DialogCompose()
      ToastCompose()
    }
  }
}

@Composable
private fun CourseCompose() {
  val courseData by remember {
    tryInitStuNum()
    CourseModel.getData()
  }.collectAsState(CourseModel.CourseData.Empty)
  val nowWeek = courseData.nowWeek
  val coursePagerState = rememberCoursePagerState(
    nowWeek = nowWeek.coerceAtLeast(0),
    pagerPosition = 0,
    pagerCount = 30,
    data = courseData.list
  )
  if (nowWeek >= 0) {
    coursePagerState.pagerPosition = nowWeek
  }
  coursePagerState.Compose()
}

private fun tryInitStuNum() {
  if (StuNumModel.stuNum == null) {
    StuNumModel.showStuNumDialog()
  }
  // 用于测试 KtProvider
  KtProviderManager.getAllImpl(ITestService::class)
    .forEach {
      log("key = ${it.key}, test = ${it.value.get().test()}")
    }
}

interface ITestService {
  fun test(): String
}