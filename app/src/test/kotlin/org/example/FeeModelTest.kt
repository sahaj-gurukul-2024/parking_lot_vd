package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class FeeModelTest {

    @Test
    fun `Concrete fee model is initialized properly`(){
        val feeModelFactory = FeeModelFactory()
        val feeModelMall = feeModelFactory.getFeeModel("Mall")
        assertNotNull(feeModelMall)
    }
}