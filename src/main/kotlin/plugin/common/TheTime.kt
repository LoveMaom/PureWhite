package com.purewhite.plugin.common

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

object TheTime {
    fun main(): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
        val formattedTime = dateFormat.format(Date())
        return dateFormat.parse(formattedTime, ParsePosition(0)).time / 1000
    }
}