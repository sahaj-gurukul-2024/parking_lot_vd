package org.example.feeModels

class Mall : FeeModel() {
    val feeStructure = mapOf(
        "Motorcycle" to 10,
        "Car" to 20,
        "Bus" to 50
    )

    override fun calculateFee(): Int {
        return 0
    }
}