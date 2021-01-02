package fr.esgi.eatroulette.infrastructure.google

import android.util.Log
import com.google.gson.JsonObject
import fr.esgi.eatroulette.BuildConfig
import fr.esgi.eatroulette.infrastructure.google.services.GeocoderService
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocoderRepository {
    private var apiService: GeocoderService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.GOOGLEAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(GeocoderService::class.java)
    }

    fun retrieveLocationFromAddress(address: String, callback: Callback<JsonObject>) {
        val call = apiService?.getLocationFromAddress(address, BuildConfig.GOOGLEKEY)
        call?.enqueue(callback)
    }
}