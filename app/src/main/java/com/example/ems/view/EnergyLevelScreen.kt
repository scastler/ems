package com.example.ems.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.ems.entity.ActivePower
import com.example.ems.repository.EnergyDTO


@Composable
fun EnergyLevelScreen(
  navController: NavHostController,
  quasarEnergyDischarged: Float,
  quasarEnergyCharged: Float,
  energyData: EnergyDTO? = null,
  activePower: ActivePower? = null
) {
  Column() {
    Text(text = "Discharged -> $quasarEnergyDischarged kWh")
    Text(text = "Charged -> $quasarEnergyCharged kWh")

    if (energyData != null) {
      LiveDataWidget(energyData)
    }
    if (activePower != null) {
      Button(onClick = { navController.navigate("detail") }) {
        Text(text = "Go to detail")
      }
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