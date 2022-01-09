package com.example.ems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.ems.repository.ActivePowerDTO
import com.example.ems.repository.EnergyDTO
import com.example.ems.ui.theme.EmsTheme
import com.example.ems.viewModel.EnergyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

  private val viewModel: EnergyViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val state by viewModel.state.collectAsState()
      EmsTheme {
        Surface(color = MaterialTheme.colors.background) {
          EnergyLevelScreen(
            3.4F,
            5.4F,
            state.energyData,
            state.activePowerList
          )
        }
      }
    }
  }
}

@Composable
fun EnergyLevelScreen(
  quasarEnergyDischarged: Float,
  quasarEnergyCharged: Float,
  energyData: EnergyDTO? = null,
  activePower: List<ActivePowerDTO>? = null
) {
  Column() {
    Text(text = "Discharged -> $quasarEnergyDischarged kWh")
    Text(text = "Charged -> $quasarEnergyCharged kWh")

    if (energyData != null) {
      LiveDataWidget(energyData)
    }
  }
}

@Composable
fun LiveDataWidget(data: EnergyDTO) {
  Text(text = "Live Data")
  Text(text = "solar_power -> ${data.solarPower}")
  Text(text = "quasars_power -> ${data.quasarsPower}")
  Text(text = "grid_power -> ${data.gridPower}")
  Text(text = "building_demand -> ${data.buildingDemand}")
  Text(text = "system_soc -> ${data.systemSoc}")
  Text(text = "total_energy -> ${data.totalEnergy}")
  Text(text = "current_energy-> ${data.currentEnergy}")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  EmsTheme {
    //EnergyLevelScreen(3.4F, 5.4F)
  }
}