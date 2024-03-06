package org.example.feeModels

import org.example.enums.*

interface FeeCalculator {
    fun calculateFare(vehicleType: VehicleType):Int
}
