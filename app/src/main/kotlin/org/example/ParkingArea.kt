package org.example

class ParkingArea(internal val venue: String, vehicleConfig: Map<String, Int>) {
    fun park(type:String) {
        if(checkAvailability(type)){

        }
        else{
            throw Exception("Slot not available")
        }
    }

    private fun checkAvailability(type: String): Boolean {
        for(every in slots[type]!!){
            if(!every.isOccupied){
                return true
            }
        }
        return false
    }

    val slots: MutableMap<String,List<Slot>> = mutableMapOf()
    init {
        for((key,value) in vehicleConfig){

            val temp = mutableListOf<Slot>()
            for(i in 0 until value){

                temp.add(Slot(i+1))
            }

            slots[key] = temp
        }
    }
}
