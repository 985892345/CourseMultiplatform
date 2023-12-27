package com.multiplatform.course.compose.course.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.toast.toast

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/18 17:24
 */
class CourseItemCompose(
  itemColor: Color,
  title: String,
  titleColor: Color,
  content: String,
  contentColor: Color,
) : IComposePresenter {

  var itemColor: Color by mutableStateOf(itemColor)

  var title: String by mutableStateOf(title)

  var titleColor: Color by mutableStateOf(titleColor)

  var content: String by mutableStateOf(content)

  var contentColor: Color by mutableStateOf(contentColor)

  var offsetX by mutableStateOf(0.dp)

  var offsetY by mutableStateOf(0.dp)

  @Composable
  override fun Compose() {
    Card(
      modifier = Modifier.fillMaxSize()
        .padding(1.6.dp),
      shape = RoundedCornerShape(8.dp),
      backgroundColor = itemColor,
      elevation = 0.5.dp
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .clickable { toast("还未实现") }
          .padding(7.dp, 8.dp)
      ) {
        Text(
          text = title,
          textAlign = TextAlign.Center,
          color = titleColor,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          fontSize = 11.sp,
          modifier = Modifier
            .fillMaxWidth()
        )
        Text(
          text = content,
          textAlign = TextAlign.Center,
          color = contentColor,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          fontSize = 11.sp,
          modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
        )
      }
    }
  }
}