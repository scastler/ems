package com.example.ems.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ems.entity.ActivePower
import com.example.ems.entity.TransactionsPerSecond

@Composable
fun ActivePowerChartsScreen(
  activePower: ActivePower
) {
  Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
    Text(
      text = "Energy Demand",
      style = MaterialTheme.typography.h4,
      textAlign = TextAlign.Start,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    )
    ActivePowerItem("Building", Color(40, 193, 218), activePower.building)
    ActivePowerItem("grid", Color(40, 20, 218), activePower.grid)
    ActivePowerItem("PV", Color(255, 0, 100), activePower.pv)
    ActivePowerItem("Quasars", Color(0, 250, 40), activePower.quasars)
  }
}

@Composable
fun ActivePowerItem(
  title: String,
  color: Color,
  transactionsPerSecond: TransactionsPerSecond
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
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp)
      )
      LinearTransactionsChart(
        modifier = Modifier
          .height(250.dp)
          .fillMaxWidth()
          .padding(12.dp),
        color = color,
        transactionsPerSecond = transactionsPerSecond
      )
    }
  }
}