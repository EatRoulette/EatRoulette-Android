package fr.esgi.eatroulette.connected.roll

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import fr.esgi.eatroulette.MainActivity
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.detail.MapsActivity
import fr.esgi.eatroulette.infrastructure.eatroulette.RestaurantRepository
import fr.esgi.eatroulette.infrastructure.google.GeocoderRepository
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_roll.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RollActivity : AppCompatActivity() {

    private var roll: Roll? = null
    private var lat: Double? = null
    private var lng: Double? = null
    private var addressFull: String = ""

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, RollActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll)

        if (!Util.isOnline()) {
            MainActivity.navigateTo(this)
        }

        roll()

        reRollBtn?.setOnClickListener {
            roll()
        }

        goToMapBtn?.setOnClickListener {
            roll?.name?.let { it1 ->
                lat?.let { it2 ->
                    lng?.let { it3 ->
                        MapsActivity.navigateTo(
                            this,
                            it1, it2, it3
                        )
                    }
                }
            }
        }
    }

    fun roll() {
        RestaurantRepository.rollRestaurant(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("eatRoll-roll", "Error : ${t.message}")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var restaurant: JsonObject? = response.body()
                restaurant = restaurant?.getAsJsonObject("restaurant")?.asJsonObject
                roll = restaurant?.let {
                    Roll(
                        it.get("name").asString,
                        it.get("type").asString,
                        it.get("address").asString,
                        it.get("city").asString,
                        it.get("website").asString,
                        it.get("postalCode").asString
                    )
                }

                restauNameTextView?.text = roll?.name
                restauTypes?.text = roll?.type
                addressFull = "${roll?.address} ${roll?.postalCode} ${roll?.city}"
                restauAddressTextView?.text = addressFull
                restauWebsite?.text = roll?.website

                GeocoderRepository.retrieveLocationFromAddress(
                    addressFull,
                    object : Callback<JsonObject> {
                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            Log.d("eatRoll-roll", "Error : ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {
                            val json = response.body()
                            val location =
                                json?.get("results")?.asJsonArray?.get(0)?.asJsonObject?.getAsJsonObject(
                                    "geometry"
                                )
                            lat = location?.asJsonObject?.getAsJsonObject("location")
                                ?.get("lat")?.asDouble
                            lng = location?.asJsonObject?.getAsJsonObject("location")
                                ?.get("lng")?.asDouble
                            Log.d("eatRoll-roll", "Loc = $lat $lng")
                        }
                    })
            }
        })
    }
}