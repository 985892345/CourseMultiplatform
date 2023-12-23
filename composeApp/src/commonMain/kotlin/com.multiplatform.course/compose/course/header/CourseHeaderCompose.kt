package com.multiplatform.course.compose.course.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.utils.painterImage
import kotlin.math.abs

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/19 10:15
 */
abstract class CourseHeaderCompose : IComposePresenter {
  abstract val nowWeek: Int
  abstract val pagerPosition: Int
  abstract val pagerPositionOffset: Float

  // 1 -> 0 -> 1
  private val fraction by derivedStateOf(structuralEqualityPolicy()) {
    minOf(abs(pagerPosition + pagerPositionOffset - nowWeek), 1F)
  }

  abstract fun clickBackToNowWeek()

  abstract fun clickSetting()

  @Composable
  override fun Compose() {
    Box(modifier = Modifier.fillMaxWidth()) {
      WeekCompose(
        nowWeek = nowWeek,
        pagerPosition = pagerPosition,
        fraction = fraction,
      )
      val backBtnWidth = 88.dp
      val backBtnPaddingEnd = 6.dp
      Box(modifier = Modifier.align(Alignment.BottomEnd)) {
        BackToNowWeekCompose(
          width = backBtnWidth,
          paddingEnd = backBtnPaddingEnd,
          fraction = fraction,
          onClick = ::clickBackToNowWeek
        )
      }
      Box(modifier = Modifier.align(Alignment.BottomEnd)) {
        SettingCompose(
          paddingEnd = backBtnWidth + backBtnPaddingEnd + 21.dp,
          fraction = fraction,
          onClick = ::clickSetting
        )
      }
    }
  }
}

@Composable
private fun WeekCompose(
  nowWeek: Int,
  pagerPosition: Int,
  fraction: Float,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Text(
      text = getWeekStr(pagerPosition),
      fontSize = 22.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(start = 16.dp, bottom = 2.dp)
        .align(Alignment.Bottom)
    )
    if (pagerPosition == nowWeek && nowWeek != 0) {
      // 整学期界面不显示本周
      Text(
        text = "(本周)",
        fontSize = 15.sp,
        modifier = Modifier
          .padding(start = 12.dp, bottom = 4.dp)
          .align(Alignment.Bottom)
          .alpha(1 - fraction / 2)
          .scale(1 - fraction / 2)
      )
    }
  }
}

@Composable
private fun BackToNowWeekCompose(
  width: Dp,
  paddingEnd: Dp,
  fraction: Float,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .padding(end = paddingEnd)
      .offset((1 - fraction) * (width + paddingEnd + 4.dp))
      .width(width)
      .height(36.dp)
      .alpha(fraction)
      .clip(RoundedCornerShape(100.dp))
      .background(
        brush = Brush.linearGradient(
          colors = listOf(Color(0xFF2921D1), Color(0xFF5D5DF7)),
          start = Offset.Zero,
          end = Offset.Infinite
        )
      ).clickable(onClick = onClick)
  ) {
    Text(
      text = "回到本周",
      color = Color.White,
      fontSize = 13.sp,
      modifier = Modifier.align(Alignment.Center),
    )
  }
}

@Composable
private fun SettingCompose(
  paddingEnd: Dp,
  fraction: Float,
  onClick: () -> Unit
) {
  val linkSize = 20.dp
  val paddingBottom = 8.dp
  Box(
    modifier = Modifier.padding(end = paddingEnd - paddingBottom)
      .size(linkSize + paddingBottom * 2)
      .offset((1 - fraction) * (paddingEnd - 15.dp))
      .clip(RoundedCornerShape(8.dp)) // 让点击阴影有圆角
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center
  ) {
    Box(
      modifier = Modifier.size(linkSize)
    ) {
      Image(
        painter = painterImage("ic_baseline_settings_24.xml"),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}

private fun getWeekStr(nowWeek: Int): String {
  return when (nowWeek) {
    0 -> "整学期"
    1 -> "第一周"
    2 -> "第二周"
    3 -> "第三周"
    4 -> "第四周"
    5 -> "第五周"
    6 -> "第六周"
    7 -> "第七周"
    8 -> "第八周"
    9 -> "第九周"
    10 -> "第十周"
    11 -> "第十一周"
    12 -> "第十二周"
    13 -> "第十三周"
    14 -> "第十四周"
    15 -> "第十五周"
    16 -> "第十六周"
    17 -> "第十七周"
    18 -> "第十八周"
    19 -> "第十九周"
    20 -> "第二十周"
    21 -> "第二十一周"
    22 -> "第二十二周"
    23 -> "第二十三周"
    24 -> "第二十四周"
    25 -> "第二十五周"
    26 -> "第二十六周"
    27 -> "第二十七周"
    28 -> "第二十八周"
    29 -> "第二十九周"
    30 -> "第三十周"
    else -> error("???")
  }
}