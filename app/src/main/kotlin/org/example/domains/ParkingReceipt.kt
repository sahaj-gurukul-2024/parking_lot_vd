package org.example.domains


import java.time.LocalDateTime


class ParkingReceipt(val entryDateTime: LocalDateTime, receiptId: Int, val exitDateTime: LocalDateTime, val price:Double) {

}
