package com.example.ems.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
  Column {
    QuasarLevelItem(title = "Quasar discharged", level = quasarEnergyDischarged)
    QuasarLevelItem(title = "Quasar charged", level = quasarEnergyCharged)

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
fun QuasarLevelItem(
  title: String,
  level: Float
) {
  Card(
    shape = RoundedCornerShape(4.dp),
    elevation = 12.dp,
    modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
  ) {
    Column {
      Text(
        text = title,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Left,
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp)
      )
      Text(
        text = "$level kWh",
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Left,
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp)
      )
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