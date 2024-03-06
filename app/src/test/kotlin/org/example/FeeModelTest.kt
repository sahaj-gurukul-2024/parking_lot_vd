package org.example

import org.example.domains.FeeModelFactory
import org.example.exceptions.ParkingException
import org.example.enums.Rate
import org.example.enums.VehicleType
import org.example.enums.Venue
import org.example.feeModels.FlatFeeCalculator
import org.junit.jupiter.api.Test
import java.time.Duration
import kotlin.test.assertEquals

class FeeModelTest {
    @Test
    fun `should return flat fee rate if parking duration is given`() {
        val flatFeeCalculator = FlatFeeCalculator(
            mapOf(
                VehicleType.CAR to 10,
                VehicleType.BUS to 20
            )
        )
        val parkingFees = flatFeeCalculator.calculateFare(VehicleType.CAR)

        assertEquals(10,parkingFees)


    }

    @Test
    fun `should calculate parking fee for car being parked in mall`() {
        val feeModelFactory = FeeModelFactory()
        val mallConfiguration = mapOf(
            VehicleType.CAR to mapOf(0 to Pair(Rate.HOURLY, 20)),
            VehicleType.MOTORCYCLE to mapOf(0 to Pair(Rate.HOURLY, 10)),
            VehicleType.BUS to mapOf(0 to Pair(Rate.HOURLY, 50))
        )

        val feeModelMall = feeModelFactory.getFeeModel(Venue.MALL)
        feeModelMall!!.setConfiguration(mallConfiguration)
        val duration = Duration.ofHours(2) + Duration.ofMinutes(30)
        val fee = feeModelMall.calculateFee(duration, VehicleType.CAR)

        assertEquals(60.0, fee)

    }

    @Test
    fun `should calculate parking fee for car being parked in stadium`() {
        val feeModelFactory = FeeModelFactory()
        val mallConfiguration = mapOf(
            VehicleType.MOTORCYCLE to mapOf(
                12 to Pair(Rate.HOURLY, 100),
                4 to Pair(Rate.HOURLY, 60),
                0 to Pair(Rate.HOURLY, 30)
            ),
            VehicleType.CAR to mapOf(
                12 to Pair(Rate.HOURLY, 200),
                4 to Pair(Rate.HOURLY, 120),
                0 to Pair(Rate.HOURLY, 60)
            )
        )

        val feeModelMall =
            feeModelFactory.getFeeModel(Venue.STADIUM) ?: throw ParkingException("Fee model is unavailable")
        feeModelMall.setConfiguration(mallConfiguration)
        val duration = Duration.ofHours(14) + Duration.ofMinutes(30)
        val fee = feeModelMall.calculateFee(duration, VehicleType.CAR)

        assertEquals(1800.0, fee)
    }
}