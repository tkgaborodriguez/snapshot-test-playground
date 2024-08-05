package com.gabo.snapshottestplayground

import android.content.Context
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import com.airbnb.android.showkase.models.Showkase
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.ParameterizedRobolectricTestRunner.Parameters
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.GraphicsMode

@RunWith(ParameterizedRobolectricTestRunner::class)
class ShowkaseScreenshotTest(
  private val testItem: TestItem
) {

  @Test
  @GraphicsMode(GraphicsMode.Mode.NATIVE)
  fun captureShowkasePreview() {
    RuntimeEnvironment.setQualifiers(testItem.deviceQualifier)

    val config = testItem.configItem
    RuntimeEnvironment.setQualifiers(config.localeQualifier)
    RuntimeEnvironment.setQualifiers(config.uiModeQualifier)
    RuntimeEnvironment.setQualifiers(config.orientationQualifier)
    RuntimeEnvironment.setFontScale(config.fontScale)
    ApplicationProvider.getApplicationContext<Context>()
      .setDisplayScale(config.displayScale)

    captureRoboImage(
      filePath = "${testItem.component.componentName}.png"
    ) {
      testItem.component.component()
    }
  }

  @After
  fun tearDown() {
    stopKoin()
  }

  companion object {
    @JvmStatic
    @Parameters
    fun data(): Array<TestItem> = combineAll(
      Showkase.getMetadata().componentList.toSet(),
      setOf(
        RobolectricDeviceQualifiers.Pixel4a,
        RobolectricDeviceQualifiers.MediumTablet,
      ),
      setOf(
        ConfigItem(
          fontScale = 0.75f,
          screenshotId = "FONT_SMALL"
        ),
        ConfigItem(
          localeQualifier = "+ar",
          uiModeQualifier = "+night",
          orientationQualifier = "+port",
          displayScale = 1.3f,
          screenshotId =
          "AR_NIGHT_PORTRAIT_DISPLAY_LARGEST",
        ),
      )
    )

    private fun combineAll(
      components: Set<ShowkaseBrowserComponent>,
      deviceQualifiers: Set<String>,
      configItems: Set<ConfigItem>,
    ): Array<TestItem> {
      val combinations: MutableList<TestItem> = mutableListOf()
      for (coffeeDrink in components) {
        for (deviceQualifier in deviceQualifiers) {
          for (configItem in configItems) {
            combinations.add(
              TestItem(
                coffeeDrink,
                deviceQualifier,
                configItem,
              )
            )
          }
        }
      }
      return combinations.toTypedArray()
    }

    data class TestItem(
      val component: ShowkaseBrowserComponent,
      val deviceQualifier: String, // Device
      val configItem: ConfigItem, // Config
    ) {
      // Unique id for an UI state, device & config combination
      // Use it to create unique screenshot file names
      val screenshotId: String =
        listOf(
          component,
          configItem.screenshotId,
          deviceQualifier,
        ).joinToString("_")
    }

    data class ConfigItem(
      val localeQualifier: String = "+en",
      val uiModeQualifier: String = "+notnight",
      val orientationQualifier: String = "+port",
      val fontScale: Float = 1.0f,
      val displayScale: Float = 1.0f,
      val screenshotId: String, // uniquely identifies this config
    )

    private fun Context.setDisplayScale(displayScale: Float) = apply {
      val density = resources.configuration.densityDpi
      val config = Configuration(resources.configuration)
      config.densityDpi = (density * displayScale).toInt()

      createConfigurationContext(config)
    }
  }
}