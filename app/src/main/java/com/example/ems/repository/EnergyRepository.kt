package com.example.ems.repository

interface EnergyRepository {
  suspend fun getLiveData(): EnergyDTO
  suspend fun getHistoryData(): List<ActivePowerDTO>
}