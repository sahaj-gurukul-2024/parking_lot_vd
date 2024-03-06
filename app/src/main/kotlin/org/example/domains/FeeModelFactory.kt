package org.example.domains

import org.example.enums.Venue
import org.example.feeModels.*

class FeeModelFactory {
    fun getFeeModel(venue: Venue): FeeModel? {
        if(venue == Venue.MALL){
            return Mall()
        }
        else if(venue== Venue.STADIUM){
            return Stadium()
        }
        return null
    }

}
