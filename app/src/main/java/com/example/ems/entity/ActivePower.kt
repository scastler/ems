package com.example.ems.entity

data class ActivePower(
  val building: TransactionsPerSecond,
  val grid: TransactionsPerSecond,
  val pv: TransactionsPerSecond,
  val quasars: TransactionsPerSecond,
)