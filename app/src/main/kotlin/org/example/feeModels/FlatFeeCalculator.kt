package org.example.feeModels

import org.example.enums.*
import org.example.exceptions.*

class FlatFeeCalculator(private val feeStructure: Map<VehicleType, Int>) : FeeCalculator {
    override fun calculateFare(vehicleType: VehicleType): Int {
        return feeStructure[vehicleType] ?: throw ParkingException("Fee structure for this vehicle unavailable")
    }
}
