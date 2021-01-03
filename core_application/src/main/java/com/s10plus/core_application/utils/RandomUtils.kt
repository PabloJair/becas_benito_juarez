package com.s10plus.core_application.utils

import kotlin.random.Random

object RandomUtils {
    fun randomString(len: Int = 15): String{
        val alphanumerics = CharArray(26) { it -> (it + 97).toChar() }.toSet()
                .union(CharArray(9) { it -> (it + 48).toChar() }.toSet())
        return (0 until len).map {
            alphanumerics.toList().random()
        }.joinToString("")
    }

    fun randomColorHex():String{

        return String.format("#%06x", Random.nextInt(0xffffff + 1))
    }
}