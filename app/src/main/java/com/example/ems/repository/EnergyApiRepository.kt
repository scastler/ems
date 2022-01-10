package com.example.ems.repository

import com.example.ems.entity.ActivePower
import com.example.ems.entity.TransactionRate
import com.example.ems.entity.TransactionsPerSecond
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

  override suspend fun getHistoryData(): ActivePower {
    return withContext(ioDispatcher) {
      val liveData = service.historicActivePower()
      if (liveData.isSuccessful && liveData.body() != null) {
        map(liveData.body()!!)
      } else {
        throw IOException()
      }
    }
  }

  private fun map(dtoList: List<ActivePowerDTO>): ActivePower {
    return ActivePower(
      mapBuilding(dtoList),
      mapGrid(dtoList),
      mapPv(dtoList),
      mapQuasars(dtoList)
    )
  }

  private fun mapBuilding(dtoList: List<ActivePowerDTO>): TransactionsPerSecond {
    val max: Double = dtoList.maxOfOrNull { it.building } ?: 0.0
    val transactionRates = dtoList.map { TransactionRate(it.date.time, it.building) }
    return TransactionsPerSecond(max, transactionRates)
  }

  private fun mapGrid(dtoList: List<ActivePowerDTO>): TransactionsPerSecond {
    val max: Double = dtoList.maxOfOrNull { it.grid } ?: 0.0
    val transactionRates = dtoList.map { TransactionRate(it.date.time, it.grid) }
    return TransactionsPerSecond(max, transactionRates)
  }

  private fun mapPv(dtoList: List<ActivePowerDTO>): TransactionsPerSecond {
    val max: Double = dtoList.maxOfOrNull { it.pv } ?: 0.0
    val transactionRates = dtoList.map { TransactionRate(it.date.time, it.pv) }
    return TransactionsPerSecond(max, transactionRates)
  }

  private fun mapQuasars(dtoList: List<ActivePowerDTO>): TransactionsPerSecond {
    val max: Double = dtoList.maxOfOrNull { it.quasars } ?: 0.0
    val transactionRates = dtoList.map { TransactionRate(it.date.time, it.quasars) }
    return TransactionsPerSecond(max, transactionRates)
  }
}