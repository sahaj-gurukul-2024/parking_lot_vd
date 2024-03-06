package org.example.feeModels

import org.example.VehicleType
import java.time.Duration
import kotlin.math.ceil


class Stadium : FeeModel() {

    private val feeStructure: Map<VehicleType, Map<Int, Int>> = mapOf(
        VehicleType.MOTORCYCLE to mapOf(12 to 100, 4 to 60, 0 to 30),
        VehicleType.CAR to mapOf(12 to 200, 4 to 120, 0 to 60),
        VehicleType.BUS to mapOf(12 to 200, 4 to 120, 0 to 60)
    )

    override fun calculateFee(duration: Duration, vehicleType: VehicleType): Double {
        val parkingHours = ceil((duration.toMinutes() / 60.0))
        var remainingHours = parkingHours
        var totalFee = 0.0
        for (everyInterval in feeStructure[vehicleType]!!) {
            if (remainingHours > everyInterval.key) {
                totalFee += (remainingHours - everyInterval.key) * everyInterval.value
                remainingHours = (everyInterval.key).toDouble()
            }
        }
        return totalFee
    }
}