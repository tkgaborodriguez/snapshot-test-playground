package com.gabo.snapshottestplayground

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.airbnb.android.showkase.models.Showkase
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Locale

@RunWith(Parameterized::class)
class ShowkaseScreenshotTest(
  private val testItem: TestItem
) {

  @get:Rule
  val paparazzi = Paparazzi(
    deviceConfig = testItem.configItem.deviceConfig
  )

  @Test
  fun captureShowkasePreview() {
    paparazzi.snapshot {
      testItem.component.component()
    }
  }
  //
  // @After
  // fun tearDown() {
  //   stopKoin()
  // }

  companion object {
    @JvmStatic
    @Parameterized.Parameters
    fun data(): Array<TestItem> = combineAll(
      Showkase.getMetadata().componentList.toSet(),
      setOf(
        Config.SMALL_SCREEN_DARK_EXTRA_LARGE_FONT_FR,
        Config.LARGE_SCREEN
      )
    )

    private fun combineAll(
      components: Set<ShowkaseBrowserComponent>,
      configItems: Set<Config>,
    ): Array<TestItem> {
      val combinations: MutableList<TestItem> = mutableListOf()
      for (coffeeDrink in components) {
        for (configItem in configItems) {
          combinations.add(
            TestItem(
              coffeeDrink,
              configItem,
            )
          )
        }
      }
      return combinations.toTypedArray()
    }

    data class TestItem(
      val component: ShowkaseBrowserComponent,
      val configItem: Config, // Config
    )

    enum class Config(
      val deviceConfig: DeviceConfig
    ) {
      LARGE_SCREEN(
        deviceConfig = DeviceConfig.PIXEL_6.copy(
          softButtons = false,
          nightMode = NightMode.NOTNIGHT,
          locale = Locale.ENGLISH.toLanguageTag()
        )
      ),

      SMALL_SCREEN_DARK_EXTRA_LARGE_FONT_FR(
        deviceConfig = DeviceConfig.NEXUS_5.copy(
          softButtons = false,
          nightMode = NightMode.NIGHT,
          fontScale = 2f,
          locale = Locale.FRENCH.toLanguageTag()
        )
      )
    }
  }
}