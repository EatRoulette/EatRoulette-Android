package fr.esgi.eatroulette.infrastructure

import fr.esgi.eatroulette.BuildConfig
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.infrastructure.services.RestaurantService
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

}