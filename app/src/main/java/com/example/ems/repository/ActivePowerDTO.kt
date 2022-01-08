package com.example.ems.repository

import com.google.gson.annotations.SerializedName
import java.util.*

data class ActivePowerDTO(
  @SerializedName("building_active_power") val building: Float,
  @SerializedName("grid_active_power") val grid: Float,
  @SerializedName("pv_active_power") val pv: Float,
  @SerializedName("quasars_active_power") val quasars: Float,
  @SerializedName("timestamp") val date: Date
)