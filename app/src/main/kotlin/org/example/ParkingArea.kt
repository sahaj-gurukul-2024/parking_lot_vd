package org.example

import java.time.LocalDateTime

class ParkingArea(internal val venue: String, vehicleConfig: Map<String, Int>) {
    val slots: MutableMap<String, List<Slot>> = mutableMapOf()
    var ticketId:Int =0
    init {
        for ((vehicleType, slotCount) in vehicleConfig) {
            slots[vehicleType] = List(slotCount) {
                Slot(it + 1)
            }
        }
    }

    private fun checkAvailability(vehicleType: String): Int {
        for (slot in slots[vehicleType]!!) {
            if (!slot.isOccupied) {
                return slot.id
            }
        }
        return 0
    }

    fun park(vehicleType: String): ParkingTicket {
        val slotId = checkAvailability(vehicleType)
        updateSpot(slotId, vehicleType)

        return ParkingTicket(ticketId, slotId, LocalDateTime.now())
    }

    private fun updateSpot(slotId: Int, vehicleType: String) {
        if (slotId != 0) {
            slots[vehicleType]!![slotId - 1].isOccupied = true
            ticketId++
        } else {
            throw Exception("Slot not available")
        }
    }

}
