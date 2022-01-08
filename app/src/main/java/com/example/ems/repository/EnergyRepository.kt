package com.example.ems.repository

interface EnergyRepository {
  suspend fun getLiveData(): EnergyDTO
}