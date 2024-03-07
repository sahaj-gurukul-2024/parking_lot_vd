package org.example

import org.example.domains.ParkingArea
import org.example.domains.ParkingReceipt
import org.example.enums.Rate
import org.example.enums.VehicleType
import org.example.enums.Venue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ParkingAreaTest {
    @Test
    fun `Parking Area is initialized properly`() {
        val vehicleConfig = mapOf<VehicleType, Int>()
        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        assertEquals(Venue.STADIUM, parkingArea.venue)
    }

    @Test
    fun `Parking Area should have correct slot numbers `() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 2, VehicleType.CAR to 0)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        assertEquals(vehicleConfig[VehicleType.CAR], parkingArea.slots[VehicleType.CAR]!!.size)
        assertEquals(vehicleConfig[VehicleType.MOTORCYCLE], parkingArea.slots[VehicleType.MOTORCYCLE]!!.size)
    }

    @Test
    fun `Parking Area should allow vehicle if slot available`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 2)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        assertDoesNotThrow {
            parkingArea.park(VehicleType.MOTORCYCLE)
        }
    }

    @Test
    fun `Parking Area should not allow vehicle if slot not available`() {
        val vehicleConfig = mutableMapOf(VehicleType.CAR to 0)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        assertThrows<Exception> {
            parkingArea.park(VehicleType.CAR)
        }
    }

    @Test
    fun `Parking Area should update spot once a vehicle is parked`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 3)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        assertDoesNotThrow { parkingArea.park(VehicleType.MOTORCYCLE) }
        assertDoesNotThrow { parkingArea.park(VehicleType.MOTORCYCLE) }
        assertDoesNotThrow { parkingArea.park(VehicleType.MOTORCYCLE) }
        assertThrows<Exception> { parkingArea.park(VehicleType.MOTORCYCLE) }
    }

    @Test
    fun `Parking Area should generate ticket properly`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 3)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        val parkingTicket = parkingArea.park(VehicleType.MOTORCYCLE)

        val ticketId = 1
        val parkingSlotId = 1

        assertEquals(ticketId, parkingTicket.id)
        assertEquals(parkingSlotId, parkingTicket.slotId)
    }

    @Test
    fun `Parking Area should generate proper ticket id`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 3)

        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)

        parkingArea.park(VehicleType.MOTORCYCLE)
        val parkingTicket = parkingArea.park(VehicleType.MOTORCYCLE)

        val ticketId = 2
        val parkingSlotId = 2

        assertEquals(ticketId, parkingTicket.id)
        assertEquals(parkingSlotId, parkingTicket.slotId)
    }

    @Test
    fun `Parking Area should update spot once a vehicle is unparked`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 1)
        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)
        val ticket = parkingArea.park(VehicleType.MOTORCYCLE)
        parkingArea.unPark(ticket, VehicleType.MOTORCYCLE)

        assertFalse(parkingArea.slots[VehicleType.MOTORCYCLE]!![0].isOccupied)
    }

    @Test
    fun `Parking Area should generate receipt`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 1)
        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)
        val ticket = parkingArea.park(VehicleType.MOTORCYCLE)
        val receipt = parkingArea.unPark(ticket, VehicleType.MOTORCYCLE)
        assertTrue(receipt is ParkingReceipt)

    }

    @Test
    fun `Parking area should have current dateTime as entry time`() {
        val vehicleConfig = mutableMapOf(VehicleType.MOTORCYCLE to 1)
        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
        val parkingArea = ParkingArea(Venue.STADIUM, vehicleConfig, feeStructure)
        val currentTime = LocalDateTime.now()
        val registeredEntryTime = parkingArea.getEntryTime()
        val difference = Duration.between(currentTime, registeredEntryTime).seconds

        assertEquals(0, difference)
    }

//    @Test
//    fun `Unparking should generate receipt with fees`() {
//        val parkingSlotConfiguration = mutableMapOf(VehicleType.MOTORCYCLE to 1)
//        val feeStructure = mapOf(VehicleType.MOTORCYCLE to mapOf(12 to Pair(Rate.HOURLY, 100)))
//        val parkingArea = ParkingArea(Venue.STADIUM, parkingSlotConfiguration, feeStructure)
//
//        val ticket = parkingArea.park(VehicleType.MOTORCYCLE)
//        val receipt = parkingArea.unPark(ticket, VehicleType.MOTORCYCLE)
//
//        assertEquals()
//
//    }
}