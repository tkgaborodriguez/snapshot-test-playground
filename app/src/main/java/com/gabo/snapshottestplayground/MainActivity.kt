package com.gabo.snapshottestplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.android.showkase.models.Showkase
import com.gabo.snapshottestplayground.ui.theme.SnapshotTestPlaygroundTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      SnapshotTestPlaygroundTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Greeting(name = "Android")
            Button(
              onClick = {
                startActivity(Showkase.getBrowserIntent(this@MainActivity))
              },
              colors = ButtonDefaults.buttonColors()
            ) {
              Text("Open Showkase Browser")
            }
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  SnapshotTestPlaygroundTheme {
    Greeting("Android")
  }
}

@Preview(showBackground = true)
@Composable
fun HelloUserPreview() {
  SnapshotTestPlaygroundTheme {
    HelloUser("Gabo")
  }
}

@Composable
fun HelloUser(userName: String, viewModel: HomeViewModel = koinViewModel()) {
  Text(text = viewModel.sayHello(userName), modifier = Modifier.padding(8.dp))
}

@Composable
fun MyCard() {
  Card(modifier = Modifier.padding(16.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "This is a card")
      Image(painter = painterResource(id = android.R.drawable.ic_dialog_info), contentDescription = null)
    }
  }
}

@Preview
@Composable
fun PreviewMyCard() {
  MyCard()
}

@Composable
fun MyRow() {
  Row(modifier = Modifier.padding(16.dp)) {
    Text(text = "Row Item 1")
    Spacer(modifier = Modifier.width(8.dp))
    Image(painter = painterResource(id = android.R.drawable.ic_dialog_info), contentDescription = null)
  }
}

@Preview
@Composable
fun PreviewMyRow() {
  MyRow()
}

@Composable
fun MyList() {
  val items = listOf("Item 1", "Item 2", "Item 3")
  LazyColumn(modifier = Modifier.padding(16.dp)) {
    items(items) { item ->
      Text(text = item)
      HorizontalDivider()
    }
  }
}

@Preview
@Composable
fun PreviewMyList() {
  MyList()
}

// Add more composables below

@Composable
fun MyTextComposable() {
  Text(text = "Hello, this is a simple text composable")
}

@Preview
@Composable
fun PreviewMyTextComposable() {
  MyTextComposable()
}

@Composable
fun MyImageComposable() {
  Image(painter = painterResource(id = android.R.drawable.ic_dialog_email), contentDescription = null)
}

@Preview
@Composable
fun PreviewMyImageComposable() {
  MyImageComposable()
}

@Composable
fun MyButtonComposable() {
  Button(onClick = {}) {
    Text(text = "Click Me")
  }
}

@Preview
@Composable
fun PreviewMyButtonComposable() {
  MyButtonComposable()
}

@Composable
fun MyColumnComposable() {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Column Item 1")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Column Item 2")
  }
}

@Preview
@Composable
fun PreviewMyColumnComposable() {
  MyColumnComposable()
}

@Composable
fun MyCheckboxComposable() {
  var checkedState = remember { mutableStateOf(true) }
  Checkbox(
    checked = checkedState.value,
    onCheckedChange = { checkedState.value = it }
  )
}

@Preview
@Composable
fun PreviewMyCheckboxComposable() {
  MyCheckboxComposable()
}

@Composable
fun MySwitchComposable() {
  var checkedState = remember { mutableStateOf(true) }
  Switch(
    checked = checkedState.value,
    onCheckedChange = { checkedState.value = it }
  )
}

@Preview
@Composable
fun PreviewMySwitchComposable() {
  MySwitchComposable()
}

@Composable
fun MySliderComposable() {
  var sliderPosition = remember { mutableStateOf(0f) }
  Slider(
    value = sliderPosition.value,
    onValueChange = { sliderPosition.value = it }
  )
}

@Preview
@Composable
fun PreviewMySliderComposable() {
  MySliderComposable()
}

@Composable
fun MyIconComposable() {
  Icon(
    painter = painterResource(id = android.R.drawable.ic_dialog_map),
    contentDescription = null
  )
}

@Preview
@Composable
fun PreviewMyIconComposable() {
  MyIconComposable()
}

@Composable
fun MyCardWithImage() {
  Card(modifier = Modifier.padding(16.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Image(painter = painterResource(id = android.R.drawable.ic_dialog_alert), contentDescription = null)
      Text(text = "Card with Image")
    }
  }
}

@Preview
@Composable
fun PreviewMyCardWithImage() {
  MyCardWithImage()
}

@Composable
fun MyRowWithText() {
  Row(modifier = Modifier.padding(16.dp)) {
    Text(text = "First Item")
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = "Second Item")
  }
}

@Preview
@Composable
fun PreviewMyRowWithText() {
  MyRowWithText()
}

@Composable
fun MyListWithImages() {
  val items = listOf(android.R.drawable.ic_dialog_dialer, android.R.drawable.ic_dialog_email, android.R.drawable.ic_dialog_map)
  LazyColumn(modifier = Modifier.padding(16.dp)) {
    items(items) { item ->
      Image(painter = painterResource(id = item), contentDescription = null)
      HorizontalDivider()
    }
  }
}

@Preview
@Composable
fun PreviewMyListWithImages() {
  MyListWithImages()
}

@Composable
fun MyCardWithText() {
  Card(modifier = Modifier.padding(16.dp)) {
    Text(text = "Simple Card with Text", modifier = Modifier.padding(16.dp))
  }
}

@Preview
@Composable
fun PreviewMyCardWithText() {
  MyCardWithText()
}

@Composable
fun MyTextWithPadding() {
  Text(text = "Text with Padding", modifier = Modifier.padding(16.dp))
}

@Preview
@Composable
fun PreviewMyTextWithPadding() {
  MyTextWithPadding()
}

@Composable
fun MyCardWithButton() {
  Card(modifier = Modifier.padding(16.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "Card with Button")
      Button(onClick = {}) {
        Text(text = "Press Me")
      }
    }
  }
}

@Preview
@Composable
fun PreviewMyCardWithButton() {
  MyCardWithButton()
}

@Composable
fun MyRowWithButton() {
  Row(modifier = Modifier.padding(16.dp)) {
    Button(onClick = {}) {
      Text(text = "Button 1")
    }
    Spacer(modifier = Modifier.width(8.dp))
    Button(onClick = {}) {
      Text(text = "Button 2")
    }
  }
}

@Preview
@Composable
fun PreviewMyRowWithButton() {
  MyRowWithButton()
}

@Composable
fun MyCardWithIcon() {
  Card(modifier = Modifier.padding(16.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Icon(painter = painterResource(id = android.R.drawable.ic_dialog_info), contentDescription = null)
      Text(text = "Card with Icon")
    }
  }
}

@Preview
@Composable
fun PreviewMyCardWithIcon() {
  MyCardWithIcon()
}

@Composable
fun MyRowWithCheckbox() {
  Row(modifier = Modifier.padding(16.dp)) {
    Text(text = "Check this box")
    Spacer(modifier = Modifier.width(8.dp))
    Checkbox(checked = true, onCheckedChange = {})
  }
}

@Preview
@Composable
fun PreviewMyRowWithCheckbox() {
  MyRowWithCheckbox()
}