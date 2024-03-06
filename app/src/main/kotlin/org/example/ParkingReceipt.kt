package org.example


import java.time.Duration
import java.time.LocalDateTime


class ParkingReceipt(val entryDateTime: LocalDateTime, val exitDateTime: LocalDateTime) {
    val duration = Duration.between(entryDateTime, exitDateTime)
}
