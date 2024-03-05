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

            val temp = mutableListOf<Slot>()
            for(i in 0 until slotCount){

                temp.add(Slot(i+1))
            }

            slots[vehicleType] = temp
        }
    }
}
