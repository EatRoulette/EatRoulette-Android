package fr.esgi.eatroulette.infrastructure.eatroulette.services


import com.google.gson.JsonObject
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.roll.Filter
import fr.esgi.eatroulette.login.Login
import fr.esgi.eatroulette.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestaurantService {

    @GET("restaurant")
    fun retrieveAllRestaurant(): Call<List<Restaurant>>

    @Headers("Content-Type: application/json")
    @POST("restaurant/rand")
    fun rollRestaurant(@Body body: Filter): Call<JsonObject>

    /**
     * Return connection token
     */
    @POST("auth/login")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body body: Login): Call<LoginResponse>

}