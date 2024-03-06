package org.example.feeModels

import org.example.VehicleType
import java.time.Duration
import kotlin.math.ceil

class Mall() : FeeModel() {

    private var feeStructure: Map<VehicleType,Map<Int,Pair<Rate,Int>>> = mapOf()

//    val mallConfiguration = mapOf(
//        VehicleType.CAR to mapOf(0 to 20),
//        VehicleType.MOTORCYCLE to mapOf(0 to 10),
//        VehicleType.BUS to mapOf(0 to 50)
//    )
    override fun setConfiguration(mallConfiguration: Map<VehicleType,Map<Int,Pair<Rate,Int>>>) {
        feeStructure= mallConfiguration
    }
    override fun calculateFee(duration: Duration, vehicleType: VehicleType): Double {
        val parkingMinutes = duration.toMinutes()
        val feeIntervals = feeStructure[vehicleType]!!
        var remainingHours = ceil(parkingMinutes/60.0)
        var totalFee = 0.0
        for((interval,pairs) in feeIntervals)
        {
            if(remainingHours>interval){
                if(pairs.first==Rate.HOURLY){
                    totalFee += pairs.second*(remainingHours-interval.toDouble())
                    remainingHours = interval.toDouble()
                }
                else{
                    totalFee += pairs.second
                    remainingHours = interval.toDouble()
                }
            }
        }
        return totalFee
    }
}