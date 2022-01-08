package com.example.ems.useCase

import com.example.ems.repository.ActivePowerDTO
import com.example.ems.repository.EnergyRepository

class GetActivePowerUseCase(
  private val energyRepository: EnergyRepository
) {
  suspend fun invoke(): List<ActivePowerDTO> = energyRepository.getHistoryData()
}