package org.example.feeModels

import java.time.Duration

abstract class FeeModel {
    abstract fun calculateFee(duration: Duration, vehicleType: String): Double
}
