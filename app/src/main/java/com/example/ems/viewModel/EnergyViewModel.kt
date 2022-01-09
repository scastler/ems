package com.example.ems.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ems.repository.ActivePowerDTO
import com.example.ems.repository.EnergyDTO
import com.example.ems.useCase.GetActivePowerUseCase
import com.example.ems.useCase.GetEnergyDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnergyViewModel(
  private val getEnergyDataUseCase: GetEnergyDataUseCase,
  private val getActivePowerUseCase: GetActivePowerUseCase
) : ViewModel() {

  private val _state: MutableStateFlow<EnergyState> = MutableStateFlow(EnergyState())
  val state: StateFlow<EnergyState> = _state

  init {
    getData()
    getHistoricActivePower()
  }

  private fun getData() {
    viewModelScope.launch {
      runCatching {
        getEnergyDataUseCase.invoke()
      }.onSuccess { energy ->
        _state.update {
          it.copy(energyData = energy)
        }
      }.onFailure { error ->
        _state.update {
          it.copy(error = error)
        }
      }
    }
  }

  private fun getHistoricActivePower() {
    viewModelScope.launch {
      runCatching {
        getActivePowerUseCase.invoke()
      }.onSuccess { activePower ->
        _state.update {
          it.copy(activePowerList = activePower)
        }
      }.onFailure { error ->
        _state.update {
          it.copy(error = error)
        }
      }
    }
  }
}

data class EnergyState(
  val energyData: EnergyDTO? = null,
  val activePowerList: List<ActivePowerDTO>? = null,
  val loading: Boolean = false,
  val error: Throwable? = null
)