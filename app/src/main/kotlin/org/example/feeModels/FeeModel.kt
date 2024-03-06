package org.example.feeModels

abstract class FeeModel {
    abstract fun calculateFee(duration: kotlin.time.Duration, vehicleType: String): Double
}
