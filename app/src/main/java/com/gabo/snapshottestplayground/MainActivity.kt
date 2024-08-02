package com.gabo.snapshottestplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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