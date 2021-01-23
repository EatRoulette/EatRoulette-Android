package fr.esgi.eatroulette.infrastructure.eatroulette

import com.google.gson.JsonObject
import fr.esgi.eatroulette.BuildConfig
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.roll.Filter
import fr.esgi.eatroulette.not_connected.login.Login
import fr.esgi.eatroulette.infrastructure.eatroulette.services.EatRouletteService
import fr.esgi.eatroulette.not_connected.login.LoginResponse
import fr.esgi.eatroulette.not_connected.register.Register
import fr.esgi.eatroulette.not_connected.register.RegisterResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object EatRouletteRepository {
    private var apiService: EatRouletteService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.EATROLLAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(EatRouletteService::class.java)
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
    fun register(data: Register, callback: Callback<RegisterResponse>) {
        val call = apiService?.register(data)
        call?.enqueue(callback)
    }
    fun login(email: String, password: String, callback: Callback<LoginResponse>) {
        val call = apiService?.login(Login(email, password))
        call?.enqueue(callback)
    }

}
