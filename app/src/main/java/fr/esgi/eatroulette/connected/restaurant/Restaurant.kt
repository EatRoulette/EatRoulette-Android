package fr.esgi.eatroulette.connected.restaurant

import java.io.Serializable

data class Restaurant(
    val name: String,
    val types: List<Type>,
    val allergens: List<Allergen>,
    val characteristics: List<Characteristic>,
    val address: String,
    val city: String,
    val website: String,
    val postalCode: String
) : Serializable
