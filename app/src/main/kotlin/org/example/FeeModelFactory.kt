package org.example

import org.example.feeModels.FeeModel
import org.example.feeModels.Mall

class FeeModelFactory {
    fun getFeeModel(venue: String): Mall? {
        if(venue=="Mall"){
            return Mall()
        }
        return null
    }

}
