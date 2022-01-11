package com.example.ems.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ems.R
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
  Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
    QuasarLevelItem(title = "Quasar discharged", level = quasarEnergyDischarged)
    QuasarLevelItem(title = "Quasar charged", level = quasarEnergyCharged)

    if (energyData != null) {
      LiveDataWidget(energyData)
    }
    if (activePower != null) {
      NavButton(
        navController = navController,
        text = "Show energy statistics",
        route = "detail"
      )
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
  QuasarLevelItem(title = "solar power", data.solarPower)
  QuasarLevelItem(title = "quasars power", data.quasarsPower)
  QuasarLevelItem(title = "grid power", data.gridPower)
  QuasarLevelItem(title = "building demand", data.buildingDemand)
  QuasarLevelItem(title = "system soc", data.systemSoc)
  QuasarLevelItem(title = "total energy", data.totalEnergy.toFloat())
  QuasarLevelItem(title = "current energy", data.currentEnergy)
}

@Composable
fun NavButton(
  navController: NavHostController,
  text: String,
  route: String
) {
  Card(
    shape = RoundedCornerShape(4.dp),
    elevation = 12.dp,
    backgroundColor = colorResource(R.color.purple_200),
    modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
      .clickable { navController.navigate(route) }
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.h6,
      color = Color.White,
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    )
  }
}