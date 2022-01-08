package com.example.ems.useCase

import com.example.ems.repository.EnergyDTO
import com.example.ems.repository.EnergyRepository

class GetEnergyDataUseCase(
  private val energyRepository: EnergyRepository
) {

  suspend fun invoke(): EnergyDTO = energyRepository.getLiveData()
}