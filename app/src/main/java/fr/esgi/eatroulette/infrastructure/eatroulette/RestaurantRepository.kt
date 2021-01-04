package fr.esgi.eatroulette.infrastructure.eatroulette

import com.google.gson.JsonObject
import fr.esgi.eatroulette.BuildConfig
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.roll.Filter
import fr.esgi.eatroulette.login.Login
import fr.esgi.eatroulette.infrastructure.eatroulette.services.RestaurantService
import fr.esgi.eatroulette.login.LoginResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestaurantRepository {
    private var apiService: RestaurantService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.EATROLLAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RestaurantService::class.java)
    }

    fun retrieveAllRestaurant(callback: Callback<List<Restaurant>>) {
        val call = apiService?.retrieveAllRestaurant()
        call?.enqueue(callback)
    }

    fun rollRestaurant(callback: Callback<JsonObject>) {
        val filterString = "{\"city\":\"PARIS\"}"
        val filter = Filter("PARIS")
        println(filterString)
        val call = apiService?.rollRestaurant(filter)
        call?.enqueue(callback)
    }

    fun login(email: String, password: String, callback: Callback<LoginResponse>) {
        val call = apiService?.login(Login(email, password))
        call?.enqueue(callback)
    }

}
