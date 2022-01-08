package com.example.ems.useCase

import com.example.ems.repository.EnergyDTO
import com.example.ems.repository.EnergyRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

class GetEnergyDataUseCaseTest {

  private val energyRepository: EnergyRepository = mock()
  private val useCase = GetEnergyDataUseCase(energyRepository)

  @Test
  fun `invoke return an EnergyDTO when energyRepository pass`() = runBlocking {
    val expectedResult = EnergyDTO(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1, 1.0f)
    whenever(energyRepository.getLiveData()).thenReturn(expectedResult)
    val result = useCase.invoke()
    assertEquals(result, expectedResult)
  }

  @Test(expected = IOException::class)
  fun `invoke return an Error when energyRepository fail`() = runBlockingTest {
    doAnswer { throw IOException() }
      .`when`(energyRepository)
      .getLiveData()
    useCase.invoke()
  }
}