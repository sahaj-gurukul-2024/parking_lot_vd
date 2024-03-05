package org.example

class ParkingArea(internal val venue: String, vehicleConfig: Map<String, Int>) {
    fun park(vehicleType:String) {
        val slotId = checkAvailability(vehicleType)
        if(slotId != 0){
            slots[vehicleType]!![slotId-1].isOccupied = true
        }
        else{
            throw Exception("Slot not available")
        }
    }

    private fun checkAvailability(vehicleType: String): Int {
        for(slot in slots[vehicleType]!!){
            if(!slot.isOccupied){
                return slot.id
            }
        }
        return 0
    }

    val slots: MutableMap<String,List<Slot>> = mutableMapOf()
    init {
        for((vehicleType, slotCount) in vehicleConfig){
            slots[vehicleType] = List(slotCount) {
                Slot(it+1)
            }
        }
    }
}
