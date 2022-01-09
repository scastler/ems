package com.example.ems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ems.ui.theme.EmsTheme
import com.example.ems.view.ActivePowerChartsScreen
import com.example.ems.view.EnergyLevelScreen
import com.example.ems.viewModel.EnergyState
import com.example.ems.viewModel.EnergyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

  private val viewModel: EnergyViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val state by viewModel.state.collectAsState()
      val navController = rememberNavController()

      EmsTheme {
        Scaffold() {
          NavigationComponent(navController, state)
        }
      }
    }
  }
}

@Composable
fun NavigationComponent(
  navController: NavHostController,
  state: EnergyState
) {
  NavHost(
    navController = navController,
    startDestination = "home"
  ) {
    composable("home") {
      EnergyLevelScreen(
        navController,
        3.4F,
        5.4F,
        state.energyData,
        state.activePowerList
      )
    }
    composable("detail") {
      ActivePowerChartsScreen()
    }
  }
}
