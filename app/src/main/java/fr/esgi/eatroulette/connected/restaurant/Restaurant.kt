package fr.esgi.eatroulette.connected.restaurant

import java.io.Serializable

data class Restaurant(val name: String, val types: List<Type>, val address: String, val city: String, val postalCode: String): Serializable
