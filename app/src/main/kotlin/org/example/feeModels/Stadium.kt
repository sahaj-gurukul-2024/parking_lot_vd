package org.example.feeModels

import java.time.Duration
import kotlin.math.ceil


class Stadium: FeeModel() {

//    val feeStruct = mapOf(
//        "Motorcycle" to mapOf(
//            Pair(0,4) to 30,
//            Pair(4,12) to 60,
//            Pair()
//        )
//    )

    val feeStructure:Map<String,Map<Int,Int>> = mapOf(
        "Motorcycle" to mapOf(12 to 100,4 to 60,0 to 30),
        "Car" to mapOf(12 to 200,4 to 120,0 to 60),
        "Suv" to mapOf(12 to 200,4 to 120,0 to 60)
    )

    override fun calculateFee(duration: Duration, vehicleType: String): Double {
        val parkingHours = ceil((duration.toMinutes()/60.0))
        var remainingHours = parkingHours
        var totalFee =0.0
        for(everyInterval in feeStructure[vehicleType]!!){
            if(remainingHours>everyInterval.key){
                totalFee += (remainingHours-everyInterval.key)*everyInterval.value
                remainingHours = (everyInterval.key).toDouble()
            }
        }
        return totalFee



    }
}