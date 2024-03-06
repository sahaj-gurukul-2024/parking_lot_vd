package org.example.feeModels

import java.time.LocalDateTime
import kotlin.math.ceil
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.times
import kotlin.time.toDuration

class Mall : FeeModel() {
    private val feeStructure = mapOf(
        "Motorcycle" to 10,
        "Car" to 20,
        "Bus" to 50
    )

    override fun calculateFee(duration: Duration, vehicleType: String): Double {
//        val duration1 = (2.5).toDuration(DurationUnit.HOURS)
//        println(duration1.inWholeHours)
        val parkingMinutes = duration.inWholeMinutes

        return feeStructure[vehicleType]!! * (ceil(parkingMinutes/60.0))
    }
}