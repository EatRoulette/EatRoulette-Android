package fr.esgi.eatroulette.connected.roll

import java.io.Serializable

data class Roll(
    val name: String,
    val type: String,
    val address: String,
    val city: String,
    val website: String,
    val postalCode: String
) : Serializable
