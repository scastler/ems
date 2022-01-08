package com.example.ems.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ems.repository.EnergyApiRepository
import com.example.ems.useCase.GetEnergyDataUseCase
import kotlinx.coroutines.launch

class EnergyViewModel(
  private val getEnergyDataUseCase: GetEnergyDataUseCase
) : ViewModel() {

  fun getData() {
    viewModelScope.launch {
      val energyData = getEnergyDataUseCase.invoke()
    }
  }
}