package com.example.ems.view

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.ems.entity.TransactionsPerSecond

@Composable
fun LinearTransactionsChart(
  modifier: Modifier = Modifier,
  color: Color,
  transactionsPerSecond: TransactionsPerSecond
) {
  if (transactionsPerSecond.transactions.isEmpty()) return

  Canvas(modifier = modifier) {
    // Total number of transactions.
    val totalRecords = transactionsPerSecond.transactions.size

    // Maximum distance between dots (transactions)
    val lineDistance = size.width / (totalRecords + 1)

    // Canvas height
    val cHeight = size.height

    // Add some kind of a "Padding" for the initial point where the line starts.
    var currentLineDistance = 0F + lineDistance

    transactionsPerSecond.transactions.forEachIndexed { index, transactionRate ->
      if (totalRecords >= index + 2) {
        drawLine(
          start = Offset(
            x = currentLineDistance,
            y = calculateYCoordinate(
              higherTransactionRateValue = transactionsPerSecond.maxTransaction,
              currentTransactionRate = transactionRate.transactionsPerSecondValue,
              canvasHeight = cHeight
            )
          ),
          end = Offset(
            x = currentLineDistance + lineDistance,
            y = calculateYCoordinate(
              higherTransactionRateValue = transactionsPerSecond.maxTransaction,
              currentTransactionRate = transactionsPerSecond.transactions[index + 1].transactionsPerSecondValue,
              canvasHeight = cHeight
            )
          ),
          color = color,
          strokeWidth = Stroke.DefaultMiter
        )
      }
      currentLineDistance += lineDistance
    }
  }
}

private fun calculateYCoordinate(
  higherTransactionRateValue: Double,
  currentTransactionRate: Double,
  canvasHeight: Float
): Float {
  val maxAndCurrentValueDifference = (higherTransactionRateValue - currentTransactionRate)
    .toFloat()
  val relativePercentageOfScreen = (canvasHeight / higherTransactionRateValue)
    .toFloat()
  return maxAndCurrentValueDifference * relativePercentageOfScreen
}
