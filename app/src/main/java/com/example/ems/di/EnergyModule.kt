package com.example.ems.di

import com.example.ems.repository.EnergyApiRepository
import com.example.ems.repository.EnergyRepository
import com.example.ems.repository.EnergyService
import com.example.ems.useCase.GetActivePowerUseCase
import com.example.ems.useCase.GetEnergyDataUseCase
import com.example.ems.viewModel.EnergyViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val energyModule = module {

  single<EnergyRepository> {
    EnergyApiRepository(getEnergyService(), Dispatchers.IO)
  }

  factory {
    GetEnergyDataUseCase(get())
  }

  factory {
    GetActivePowerUseCase(get())
  }

  viewModel { EnergyViewModel(get(), get()) }
}

private val BASE_URL = "https://gitlab.com/carandahe/ems-demo-project/-/raw/main/"

private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

private fun getEnergyService(): EnergyService {
  return Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(EnergyService::class.java)
}