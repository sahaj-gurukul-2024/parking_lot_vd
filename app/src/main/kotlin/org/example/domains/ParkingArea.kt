package org.example.domains

import org.example.enums.VehicleType
import java.time.Duration
import java.time.LocalDateTime

class ParkingArea(internal val venue: String, vehicleConfig: Map<VehicleType, Int>) {
    val slots: MutableMap<VehicleType, List<Slot>> = mutableMapOf()
    private var ticketId: Int = 0

    init {
        for ((vehicleType, slotCount) in vehicleConfig) {
            slots[vehicleType] = List(slotCount) {
                Slot(it + 1)
            }
        }
    }

    private fun checkAvailability(vehicleType: VehicleType): Int {
        for (slot in slots[vehicleType]!!) {
            if (!slot.isOccupied) {
                return slot.id
            }
        }
        return 0
    }

    fun park(vehicleType: VehicleType): ParkingTicket {
        val slotId = checkAvailability(vehicleType)
        updateSpot(slotId, vehicleType)
        val entryTime = getEntryTime()
        return ParkingTicket(ticketId, slotId, entryTime)
    }

    internal fun getEntryTime(): LocalDateTime {
        return LocalDateTime.now()
    }


    private fun updateSpot(slotId: Int, vehicleType: VehicleType) {
        if (slotId != 0) {
            slots[vehicleType]!![slotId - 1].isOccupied = true
            ticketId++
        } else {
            throw Exception("Slot not available")
        }
    }

    fun unPark(ticket: ParkingTicket, vehicleType: VehicleType): ParkingReceipt {
        for (slot in slots[vehicleType]!!) {
            if (slot.id == ticket.slotId) {
                slot.isOccupied = false
            }
        }
        return ParkingReceipt(ticket.entryDateTime, LocalDateTime.now())
    }

    private fun calculateDuration(entryTime: LocalDateTime, exitTime: LocalDateTime): Duration? {
        return Duration.between(exitTime, entryTime)
    }
}
