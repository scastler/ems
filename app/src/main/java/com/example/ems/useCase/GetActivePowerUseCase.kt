package com.example.ems.useCase

import com.example.ems.entity.ActivePower
import com.example.ems.repository.EnergyRepository

class GetActivePowerUseCase(
  private val energyRepository: EnergyRepository
) {
  suspend fun invoke(): ActivePower = energyRepository.getHistoryData()
}