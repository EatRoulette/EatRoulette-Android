package fr.esgi.eatroulette.infrastructure.services


import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.infrastructure.User
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantService {

    @GET("restaurant")
    fun retrieveAllRestaurant(): Call<List<Restaurant>>

    @GET("json/get/VydTXyeqY?delay=2000")
    fun retrieveRestaurant(): Call<User>

    @GET("json/get/VydTXyeqY?delay=2000")
    fun retrieveUser(): Call<User>
}