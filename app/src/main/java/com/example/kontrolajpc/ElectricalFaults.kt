@file:Suppress("NonAsciiCharacters", "EnumEntryName")

package com.example.kontrolajpc

enum class ElectricalFaults {
    Cee_Priklop,
    Razdelilec,
    Kabli,
    Vezava_Razvodnice,
    Finomontaža,
    Kanal;

    companion object {
        fun getPositionByName(name: String): Int {
            return entries.toTypedArray().indexOfFirst {
                it.name.equals(name, ignoreCase = true)
            }
        }
    }

}