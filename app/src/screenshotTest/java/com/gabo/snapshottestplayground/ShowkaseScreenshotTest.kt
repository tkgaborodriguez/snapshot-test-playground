package com.gabo.snapshottestplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gabo.snapshottestplayground.ui.theme.SnapshotTestPlaygroundTheme

class ShowkaseScreenshotTest {

  @Preview(showBackground = true)
  @Composable
  fun GreetingPreview() {
    SnapshotTestPlaygroundTheme {
      Greeting("Android")
    }
  }

  @Preview
  @Composable
  fun PreviewMyCard() {
    MyCard()
  }

  @Preview
  @Composable
  fun PreviewMyRow() {
    MyRow()
  }

  @Preview
  @Composable
  fun PreviewMyList() {
    MyList()
  }

  @Preview
  @Composable
  fun PreviewMyTextComposable() {
    MyTextComposable()
  }

  @Preview
  @Composable
  fun PreviewMyImageComposable() {
    MyImageComposable()
  }
}