package com.example.ems.viewModel

import com.example.ems.repository.ActivePowerDTO
import com.example.ems.repository.EnergyDTO
import com.example.ems.useCase.GetActivePowerUseCase
import com.example.ems.useCase.GetEnergyDataUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import java.io.IOException
import java.text.SimpleDateFormat

class EnergyViewModelTest {

  private val getEnergyDataUseCase: GetEnergyDataUseCase = mock()
  private val getActivePowerUseCase: GetActivePowerUseCase = mock()

  val energyData = EnergyDTO(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1, 1.0f)
  val activePoserList = listOf(ActivePowerDTO(1.0f, 1.0f, 1.0f, 1.0f, SimpleDateFormat("dd-MM-yyyy").parse("14-02-2018")))

  @ExperimentalCoroutinesApi
  private val testDispatcher = TestCoroutineDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }

  @ExperimentalCoroutinesApi
  @Test
  fun `state with energy and active power loaded`() = runBlockingTest {
    doAnswer { energyData }
      .`when`(getEnergyDataUseCase)
      .invoke()
    doAnswer { activePoserList }
      .`when`(getActivePowerUseCase)
      .invoke()

    val energyViewModel = EnergyViewModel(getEnergyDataUseCase, getActivePowerUseCase)
    assertEquals(energyViewModel.state.value.error, null)
    assertEquals(energyViewModel.state.value.energyData, energyData)
    assertEquals(energyViewModel.state.value.activePower, activePoserList)
  }

  @ExperimentalCoroutinesApi
  @Test
  fun `state with IOError when loading energyData`() = runBlockingTest {
    val error = IOException()
    doAnswer { throw error }
      .`when`(getEnergyDataUseCase)
      .invoke()
    doAnswer { activePoserList }
      .`when`(getActivePowerUseCase)
      .invoke()

    val energyViewModel = EnergyViewModel(getEnergyDataUseCase, getActivePowerUseCase)
    assertEquals(energyViewModel.state.value.error, error)
    assertEquals(energyViewModel.state.value.energyData, null)
    assertEquals(energyViewModel.state.value.activePower, activePoserList)
  }
}