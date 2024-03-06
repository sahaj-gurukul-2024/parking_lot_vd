package org.example.feeModels

import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.ceil

class Mall : FeeModel() {
    private val feeStructure = mapOf(
        "Motorcycle" to 10,
        "Car" to 20,
        "Bus" to 50
    )

    override fun calculateFee(duration: Duration, vehicleType: String): Double {
        val parkingMinutes = duration.toMinutes()
        return feeStructure[vehicleType]!! * (ceil(parkingMinutes/60.0))
    }
}