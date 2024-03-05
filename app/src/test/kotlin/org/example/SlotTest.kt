package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class SlotTest {

    @Test
    fun `Parking Slot should be initialized properly`(){
        val id = 1

        val parkingSlot = Slot(id)

        assertEquals(id,parkingSlot.id)
        assertFalse(parkingSlot.isOccupied)
    }

}