package org.example.feeModels

import org.example.VehicleType
import java.time.Duration

abstract class FeeModel {
    abstract fun calculateFee(duration: Duration, vehicleType: VehicleType): Double
    abstract fun setConfiguration(mallConfiguration: Map<VehicleType,Map<Int,Pair<Rate,Int>>>)
}
