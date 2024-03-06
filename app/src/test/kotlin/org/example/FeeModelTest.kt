package org.example

import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class FeeModelTest {

    @Test
    fun `Concrete fee model is initialized properly`(){
        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Mall")

        assertNotNull(feeModelMall)
    }

    @Test
    fun `Should Calculate fee correctly in mall when entry and exit times are given`(){

        val feeModelFactory = FeeModelFactory()

        val feeModelMall = feeModelFactory.getFeeModel("Mall")
        val duration = (2.5).toDuration(DurationUnit.HOURS)
        val vehicleType = "Car"

        val fee = feeModelMall?.calculateFee(duration,vehicleType)

        assertEquals(60.0,fee)

    }
}