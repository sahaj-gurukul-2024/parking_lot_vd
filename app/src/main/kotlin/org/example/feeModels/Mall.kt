package org.example.feeModels

import org.example.VehicleType
import java.time.Duration
import kotlin.math.ceil

class Mall : FeeModel() {
    private val feeStructure = mapOf(
        VehicleType.MOTORCYCLE to 10,
        VehicleType.CAR to 20,
        VehicleType.BUS to 50
    )

    override fun calculateFee(duration: Duration, vehicleType: VehicleType): Double {
        val parkingMinutes = duration.toMinutes()
        return feeStructure[vehicleType]!! * (ceil(parkingMinutes/60.0))
    }
}