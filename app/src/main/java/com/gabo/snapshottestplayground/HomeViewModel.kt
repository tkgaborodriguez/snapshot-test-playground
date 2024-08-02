package com.gabo.snapshottestplayground

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

  fun sayHello(name: String): String {
    return "Hello ${findUser(name)}"
  }

  private fun findUser(name: String) = "$name the person"
}