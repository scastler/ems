package com.example.ems.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class EnergyApiRepository(
  private val service: EnergyService,
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default
) : EnergyRepository {

  override suspend fun getLiveData(): EnergyDTO {
    return withContext(ioDispatcher) {
      val liveData = service.liveData()
      if (liveData.isSuccessful && liveData.body() != null) {
        liveData.body()!!
      } else {
        throw IOException()
      }
    }
  }

  override suspend fun getHistoryData(): List<ActivePowerDTO> {
    return withContext(ioDispatcher) {
      val liveData = service.historicActivePower()
      if (liveData.isSuccessful && liveData.body() != null) {
        liveData.body()!!
      } else {
        throw IOException()
      }
    }
  }
}