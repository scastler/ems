package com.example.ems.repository

import retrofit2.Response
import retrofit2.http.GET

interface EnergyService {

  @GET("live_data.json")
  suspend fun liveData(): Response<EnergyDTO>

  @GET("historic_data.json")
  suspend fun historicActivePower(): Response<List<ActivePowerDTO>>
}