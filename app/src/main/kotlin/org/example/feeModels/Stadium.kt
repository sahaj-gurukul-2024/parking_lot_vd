package org.example.feeModels

import org.example.VehicleType
import org.example.exceptions.ParkingException
import java.time.Duration
import kotlin.math.ceil


class Stadium : FeeModel() {
    private var feeStructure: Map<VehicleType, Map<Int, Pair<Rate, Int>>> = mapOf()
    override fun setConfiguration(mallConfiguration: Map<VehicleType, Map<Int, Pair<Rate, Int>>>) {
        feeStructure = mallConfiguration
    }

    override fun calculateFee(duration: Duration, vehicleType: VehicleType): Double {
        val parkingMinutes = duration.toMinutes()
        val feeIntervals = feeStructure[vehicleType] ?: throw ParkingException("Fee Structure not available")
        var remainingHours = ceil(parkingMinutes / 60.0)
        var totalFee = 0.0
        for ((interval, pairs) in feeIntervals) {
            if (remainingHours > interval) {
                if (pairs.first == Rate.HOURLY) {
                    totalFee += pairs.second * (remainingHours - interval.toDouble())
                    remainingHours = interval.toDouble()
                } else if (pairs.first == Rate.FLAT) {
                    totalFee += pairs.second
                    remainingHours = interval.toDouble()
                }
            }
        }
        return totalFee
    }
}