package org.example.feeModels

import org.example.enums.VehicleType
import org.example.enums.Rate
import org.example.exceptions.ParkingException
import java.time.Duration
import kotlin.math.ceil

class Mall : FeeModel() {

    private var feeStructure: Map<VehicleType, Map<Int, Pair<Rate, Int>>> = mapOf()
//    private var feeStructure2: Map<VehicleType, Map<Int, Int>> = mapOf()
    override fun setConfiguration(mallConfiguration: Map<VehicleType, Map<Int, Pair<Rate, Int>>>) {
        feeStructure = mallConfiguration
    }

//    fun setConfiguration(mallConfiguration: Map<VehicleType, Map<Int, Int>>) {
//        feeStructure2 = mallConfiguration
//    }

    override fun calculateFee(duration: Duration, vehicleType: VehicleType): Double {
        val parkingMinutes = duration.toMinutes()
        val feeIntervals = feeStructure[vehicleType] ?: throw ParkingException("Fee Model is not available")
        var remainingHours = ceil(parkingMinutes / 60.0)
        var totalFee = 0.0
        for ((interval, pairs) in feeIntervals) {
            if (remainingHours > interval) {
                if (pairs.first == Rate.HOURLY) {
                    totalFee += pairs.second * (remainingHours - interval.toDouble())
                    remainingHours = interval.toDouble()
                } else {
                    if (pairs.first == Rate.FLAT) {
                        totalFee += pairs.second
                        remainingHours = interval.toDouble()
                    }
                }
            }
        }
        return totalFee
    }

//    fun calculateFare(duration: kotlin.time.Duration, vehicle: VehicleType): Int {
//        val feeIntervals = feeStructure2[vehicle] ?: throw ParkingException("Fee interval missing")
//        var totalFee = 0
//        for(interval in feeIntervals)
//        {
//            if(interval.key== Int.MAX_VALUE)
//            {
//                val hourlyFeeCalculator = HourlyFeeCalculator()
//                totalFee +=
//
//            }
//        }
//    }
}