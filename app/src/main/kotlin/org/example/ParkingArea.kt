package org.example

class ParkingArea(internal val venue: String, vehicleConfig: Map<String, Int>) {
    fun park(vehicleType:String) {
        if(checkAvailability(vehicleType)){

        }
        else{
            throw Exception("Slot not available")
        }
    }

    private fun checkAvailability(vehicleType: String): Boolean {
        for(slot in slots[vehicleType]!!){
            if(!slot.isOccupied){
                return true
            }
        }
        return false
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
