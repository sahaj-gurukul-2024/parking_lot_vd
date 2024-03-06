package org.example

import org.example.domains.ParkingTicket
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ParkingTicketTest {

    @Test
    fun `parking ticket should be initialised properly`() {
        val ticketId = 1
        val parkingSlotId = 1
        val entryDateTime = LocalDateTime.now()

        val parkingTicket = ParkingTicket(ticketId, parkingSlotId, entryDateTime)

        assertEquals(ticketId, parkingTicket.id)
        assertEquals(parkingSlotId, parkingTicket.slotId)
        assertEquals(entryDateTime, parkingTicket.entryDateTime)
    }
}