package com.example.ems.repository

import com.google.gson.annotations.SerializedName
import java.util.*

data class ActivePowerDTO(
  @SerializedName("building_active_power") val building: Double,
  @SerializedName("grid_active_power") val grid: Double,
  @SerializedName("pv_active_power") val pv: Double,
  @SerializedName("quasars_active_power") val quasars: Double,
  @SerializedName("timestamp") val date: Date
)