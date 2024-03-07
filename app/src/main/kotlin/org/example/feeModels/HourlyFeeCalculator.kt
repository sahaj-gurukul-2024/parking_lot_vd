package org.example.feeModels

import org.example.enums.*
import org.example.exceptions.ParkingException
import kotlin.time.Duration

class HourlyFeeCalculator(private val feeStructure: Map<VehicleType, Int>, private val duration: Duration) :
    FeeCalculator {
    override fun calculateFare(vehicleType: VehicleType): Int {
        val noOfMinutes = duration.inWholeMinutes
        val noOfHours = duration.inWholeHours
        val perHourRate = feeStructure[vehicleType] ?: throw ParkingException("No per hour rates for this vehicle type")

        return if ((noOfMinutes % 60).toInt() == 0) {
            perHourRate.times(noOfHours).toInt()
        } else {
            perHourRate.times(noOfHours + 1).toInt()
        }
    }
}
