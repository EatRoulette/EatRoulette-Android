package fr.esgi.eatroulette.infrastructure.services


import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.infrastructure.User
import fr.esgi.eatroulette.infrastructure.Login
import fr.esgi.eatroulette.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface RestaurantService {

    @GET("restaurant")
    fun retrieveAllRestaurant(): Call<List<Restaurant>>

    @GET("json/get/VydTXyeqY?delay=2000")
    fun retrieveRestaurant(): Call<User>

    @GET("json/get/VydTXyeqY?delay=2000")
    fun retrieveUser(): Call<User>

    // TODO create another service with auth prefix?
    // return token
    @POST("auth/login")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body body: Login): Call<LoginResponse>
}