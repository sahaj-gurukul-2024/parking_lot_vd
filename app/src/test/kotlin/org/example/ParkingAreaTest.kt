package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ParkingAreaTest {

    @Test
    fun `Parking Area is initialized properly`() {
        val venue = "stadium"
        val vehicleConfig = mapOf<String,Int>()

        val parkingArea = ParkingArea(venue,vehicleConfig)

        assertEquals(venue, parkingArea.venue)
    }

    @Test
    fun `Parking Area should have correct slot numbers `() {
        val venue = "stadium"
        val vehicleConfig = mutableMapOf("Motorcycles" to 2, "Cars" to 0)

        val parkingArea = ParkingArea(venue, vehicleConfig)

        assertEquals(vehicleConfig["Cars"], parkingArea.slots["Cars"]!!.size)
        assertEquals(vehicleConfig["Motorcycles"], parkingArea.slots["Motorcycles"]!!.size)
    }

    @Test
    fun `Parking Area should allow vehicle if slot available`() {
        val venue = "stadium"
        val vehicleConfig = mutableMapOf("Motorcycles" to 2, "Cars" to 0)
        val parkingArea = ParkingArea(venue, vehicleConfig)

        assertDoesNotThrow{
            parkingArea.park("Motorcycles")
        }
    }

    @Test
    fun `Parking Area should not allow vehicle if slot not available`() {
        val venue = "stadium"
        val vehicleConfig = mutableMapOf("Motorcycles" to 2, "Cars" to 0)
        val parkingArea = ParkingArea(venue, vehicleConfig)

        assertThrows<Exception>{
            parkingArea.park("Cars")
        }
    }

}