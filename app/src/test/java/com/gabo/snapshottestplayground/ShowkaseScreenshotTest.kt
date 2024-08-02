package com.gabo.snapshottestplayground

import com.airbnb.android.showkase.models.Showkase
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.ParameterizedRobolectricTestRunner.Parameters
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(ParameterizedRobolectricTestRunner::class)
class ShowkaseScreenshotTest(private val component: ShowkaseBrowserComponent) {

  @Test
  fun captureShowkasePreview() {
    captureRoboImage {
      component.component()
    }
  }

  @After
  fun tearDown() {
    stopKoin()
  }

  companion object {
    @JvmStatic
    @Parameters
    fun data(): Collection<Array<ShowkaseBrowserComponent>> {
      return Showkase.getMetadata().componentList.map { arrayOf(it) }
    }
  }
}