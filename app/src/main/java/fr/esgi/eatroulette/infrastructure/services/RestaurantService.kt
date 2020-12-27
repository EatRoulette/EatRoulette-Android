package fr.esgi.eatroulette.infrastructure.services


import fr.esgi.eatroulette.connected.restaurant.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantService {

    @GET("restaurant")
    fun retrieveAllRestaurant(): Call<List<Restaurant>>

}