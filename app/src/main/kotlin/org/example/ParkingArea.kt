package org.example

class ParkingArea(internal val venue: String, vehicleConfig: Map<String, Int>) {

    val slots: MutableMap<String,List<Int>> = mutableMapOf()
    init {
        for((key,value) in vehicleConfig){

            val temp = mutableListOf<Int>()
            for(i in 0 until value){
                temp.addLast(i+1)
            }

            slots[key] = temp
        }
    }
}
