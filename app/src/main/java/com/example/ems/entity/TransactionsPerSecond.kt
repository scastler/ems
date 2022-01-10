package com.example.ems.entity

data class TransactionsPerSecond(
  val maxTransaction: Double,
  val transactions: List<TransactionRate>
)

/**
 * Represents a transaction rate.
 * @param timeStamp the time stamp of the transaction.
 * @param transactionsPerSecondValue the quantity of transactions made per second.
 */
data class TransactionRate(
  val timeStamp: Long,
  val transactionsPerSecondValue: Double
)