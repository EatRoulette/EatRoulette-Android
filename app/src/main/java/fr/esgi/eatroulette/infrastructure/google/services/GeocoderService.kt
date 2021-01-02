package fr.esgi.eatroulette.infrastructure.google.services

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocoderService {

    @GET("geocode/json")
    fun getLocationFromAddress(@Query(value="address", encoded = true) address: String, @Query(value="key") key: String): Call<JsonObject>

}