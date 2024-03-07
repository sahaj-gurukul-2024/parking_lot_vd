package org.example.domains

import org.example.enums.Venue
import org.example.feeModels.*

class FeeModelFactory {
    fun getFeeModel(venue: Venue): FeeModel{
        return when (venue) {
            Venue.MALL -> {
                Mall()
            }
            Venue.STADIUM -> {
                Stadium()
            }
        }
    }

}
