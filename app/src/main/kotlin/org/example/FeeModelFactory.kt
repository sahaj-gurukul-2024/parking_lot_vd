package org.example

import org.example.feeModels.FeeModel
import org.example.feeModels.Mall
import org.example.feeModels.Stadium

class FeeModelFactory {
    fun getFeeModel(venue: String): FeeModel? {
        if(venue=="Mall"){
            return Mall()
        }
//        else if(venue=="Stadium"){
//            return Stadium()
//        }
        return null
    }

}
