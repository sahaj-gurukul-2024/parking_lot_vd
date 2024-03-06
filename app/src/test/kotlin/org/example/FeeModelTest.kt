package org.example

import org.junit.jupiter.api.Test
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FeeModelTest {

    @Test
    fun `Concrete fee model is initialized properly`(){
        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Mall")

        assertNotNull(feeModelMall)
    }

    @Test
    fun `Should Calculate fee correctly in mall when duration is given`(){

        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Mall")
        val duration = Duration.ofHours(2)+ Duration.ofMinutes(30)
        val vehicleType = "Car"

        val fee = feeModelMall?.calculateFee(duration,vehicleType)

        assertEquals(60.0,fee)

    }

    @Test
    fun `should calculate parking fee for stadium when duration is given`()
    {
        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Stadium")
        val duration = Duration.ofHours(14) + Duration.ofMinutes(30)
        println(duration.toMinutes())
        val vehicleType = "Car"

        val fee = feeModelMall?.calculateFee(duration,vehicleType)

        assertEquals(1800.0,fee)

    }
}