package com.example.ems.repository

import com.google.gson.annotations.SerializedName

data class EnergyDTO(
  @SerializedName("solar_power") val solarPower: Float,
  @SerializedName("quasars_power") val quasarsPower : Float,
  @SerializedName("grid_power") val gridPower : Float,
  @SerializedName("building_demand") val buildingDemand : Float,
  @SerializedName("system_soc") val systemSoc : Float,
  @SerializedName("total_energy") val totalEnergy : Int,
  @SerializedName("current_energy") val currentEnergy : Float
)
