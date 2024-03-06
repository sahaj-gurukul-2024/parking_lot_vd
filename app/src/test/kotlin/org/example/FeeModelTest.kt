package org.example

import org.example.feeModels.Rate
import org.junit.jupiter.api.Test
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FeeModelTest {

    @Test
    fun `should calculate parking fee for car in mall `(){
        val feeModelFactory = FeeModelFactory()
        val mallConfiguration = mapOf(
            VehicleType.CAR to mapOf(0 to Pair(Rate.HOURLY,20)),
            VehicleType.MOTORCYCLE to mapOf(0 to Pair(Rate.HOURLY,10)),
            VehicleType.BUS to mapOf(0 to Pair(Rate.HOURLY,50))
        )

        val feeModelMall = feeModelFactory.getFeeModel("Mall")
        feeModelMall!!.setConfiguration(mallConfiguration)

        val duration = Duration.ofHours(2)+ Duration.ofMinutes(30)

        val fee = feeModelMall.calculateFee(duration,VehicleType.CAR)

        assertEquals(60.0,fee)

    }

    @Test
    fun `Should Calculate fee correctly in mall when duration is given`(){
        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Mall")
        val duration = Duration.ofHours(2)+ Duration.ofMinutes(30)

        val fee = feeModelMall?.calculateFee(duration,VehicleType.CAR)

        assertEquals(60.0,fee)

    }

    @Test
    fun `should calculate parking fee for stadium when duration is given`()
    {
        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Stadium")
        val duration = Duration.ofHours(14) + Duration.ofMinutes(30)
        println(duration.toMinutes())

        val fee = feeModelMall?.calculateFee(duration,VehicleType.CAR)

        assertEquals(1800.0,fee)

    }
}