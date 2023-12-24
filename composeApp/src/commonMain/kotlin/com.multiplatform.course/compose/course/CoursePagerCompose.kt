package com.multiplatform.course.compose.course

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.multiplatform.course.compose.base.IComposePresenter
import com.multiplatform.course.compose.course.combine.CourseCombineCompose
import com.multiplatform.course.compose.course.header.CourseHeaderCompose
import com.multiplatform.course.compose.course.layout.CourseLayoutCompose
import com.multiplatform.course.model.StuNumModel
import com.multiplatform.course.utils.Today
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus

/**
 * .
 *
 * @author 985892345
 * @date 2023/12/20 19:08
 */
abstract class CoursePagerCompose : IComposePresenter {

  abstract val data: List<PagerItemState>

  abstract val nowWeek: Int

  abstract val pagerCount: Int

  private val mWeekDataMap by derivedStateOf(structuralEqualityPolicy()) {
    buildMap<Int, MutableList<PagerItemState>> {
      data.forEach {
        getOrPut(it.week) { mutableListOf() }.add(it)
      }
    }
  }

  private val mCombineComposes = mutableListOf<CourseCombineComposeImpl>()

  val combineComposes: List<CourseCombineCompose> by derivedStateOf(structuralEqualityPolicy()) {
    mCombineComposes.apply {
      repeat(pagerCount - size) {
        add(CourseCombineComposeImpl(size))
      }
      repeat(size - pagerCount) {
        removeLast()
      }
    }.toMutableList()
  }

  private var currentPage: Int by mutableIntStateOf(0)

  private var targetPage: Int by mutableIntStateOf(0)

  var pagerPosition: Int
    get() = currentPage
    set(value) {
      targetPage = value
    }

  private var pagerPositionOffset by mutableFloatStateOf(0F)

  val headerCompose = object : CourseHeaderCompose() {
    override val nowWeek: Int get() = this@CoursePagerCompose.nowWeek
    override val pagerPosition: Int get() = this@CoursePagerCompose.currentPage
    override val pagerPositionOffset: Float get() = this@CoursePagerCompose.pagerPositionOffset

    override fun clickBackToNowWeek() {
      this@CoursePagerCompose.pagerPosition = nowWeek
    }

    override fun clickSetting() {
      StuNumModel.showStuNumDialog()
    }
  }

  inner class CourseCombineComposeImpl(
    val week: Int,
  ) : CourseCombineCompose(getMonDate(week)) {
    override val layoutItems: List<CourseLayoutCompose.LayoutItem> by derivedStateOf(structuralEqualityPolicy()) {
      (mWeekDataMap[week] ?: emptyList()).map {
        it.layoutItem
      }
    }
  }

  // 获取 week 页对应的星期一日期，如果 week = 0，则说明是整学期，返回 null
  private fun getMonDate(week: Int): LocalDate? {
    if (week <= 0) return null
    return Today.minus(Today.dayOfWeek.ordinal, DateTimeUnit.DayBased(1))
      .minus((nowWeek - week) * 7, DateTimeUnit.DayBased(1))
  }

  @Stable
  class PagerItemState(
    val week: Int,
    val layoutItem: CourseLayoutCompose.LayoutItem,
  )

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  override fun Compose() {
    Column(
      modifier = Modifier
        .background(Color.White)
    ) {
      val pagerState = rememberPagerState(initialPage = nowWeek) { pagerCount }
      scrollToPosition(pagerState)
      observeTargetPage(pagerState)
      observeCurrentPage(pagerState)
      observePageOffsetFraction(pagerState)
      Box(modifier = Modifier.height(54.dp).fillMaxWidth()) {
        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 3.dp)) {
          headerCompose.Compose()
        }
      }
      HorizontalPager(state = pagerState, beyondBoundsPageCount = 1) {
        combineComposes[it].Compose()
      }
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun observeCurrentPage(pagerState: PagerState) {
    currentPage = pagerState.currentPage
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun observeTargetPage(pagerState: PagerState) {
    if (!pagerState.isScrollInProgress) {
      targetPage = pagerState.targetPage
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun observePageOffsetFraction(pagerState: PagerState) {
    pagerPositionOffset = pagerState.currentPageOffsetFraction
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun scrollToPosition(pagerState: PagerState) {
    LaunchedEffect(targetPage) {
      pagerState.animateScrollToPage(targetPage)
    }
  }
}

@Composable
fun rememberCoursePagerState(
  nowWeek: Int,
  pagerPosition: Int,
  pagerCount: Int,
  data: List<CoursePagerCompose.PagerItemState>,
): CoursePagerCompose = remember {
  object : CoursePagerCompose() {
    override var pagerCount: Int by mutableIntStateOf(pagerCount)
    override var data: List<PagerItemState> by mutableStateOf(data)
    override var nowWeek: Int by mutableIntStateOf(nowWeek)
  }
}.apply {
  this.pagerCount = pagerCount
  this.data = data
  this.nowWeek = nowWeek
  this.pagerPosition = pagerPosition
}



