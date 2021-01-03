package fr.esgi.eatroulette.infrastructure

import android.util.Log
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.infrastructure.services.RestaurantService
import fr.esgi.eatroulette.login.LoginResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository
{
    private var apiService: RestaurantService? = null

    init
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") //https://whispering-cove-53853.herokuapp.com/
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RestaurantService::class.java)
    }

    fun retrieveUser(callback: Callback<User>)
    {
        val call = apiService?.retrieveUser()
        call?.enqueue(callback)
    }

    fun login(email: String, password: String, callback: Callback<LoginResponse>)
    {
        val call = apiService?.login(Login(email, password))
        call?.enqueue(callback)
    }

    fun retrieveAllRestaurant(callback: Callback<List<Restaurant>>){
        val call = apiService?.retrieveAllRestaurant()
        call?.enqueue(callback)
    }

}