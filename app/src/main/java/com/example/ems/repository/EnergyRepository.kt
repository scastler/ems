package com.example.ems.repository

import com.example.ems.entity.ActivePower

interface EnergyRepository {
  suspend fun getLiveData(): EnergyDTO
  suspend fun getHistoryData(): ActivePower
}