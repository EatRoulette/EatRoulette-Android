package fr.esgi.eatroulette.connected.restaurant.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.restaurant.list.RestaurantAdapter
import fr.esgi.eatroulette.infrastructure.google.GeocoderRepository
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantDetailActivity : AppCompatActivity() {

    companion object {
        fun navigateTo(context: Context, restaurant: Restaurant) {
            val intent = Intent(context, RestaurantDetailActivity::class.java)
            intent.putExtra("restaurant", restaurant)
            context.startActivity(intent)
        }
    }

    private var restaurant: Restaurant? = null
    private var lat: Double? = null
    private var lng: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        restaurant = intent?.getSerializableExtra("restaurant") as Restaurant?
        val addressFull = "${restaurant?.address} ${restaurant?.postalCode} ${restaurant?.city}"
        restauNameTextView?.text = restaurant?.name

        var types = ""
        restaurant?.types?.forEach {
            types += "${it.name} "
        }
        if (types != "") {
            restauTypes?.text = "Types : $types"
        } else {
            restauTypes?.text = resources?.getString(R.string.noTypes)
        }

        var allergens = ""
        restaurant?.allergens?.forEach {
            allergens += "${it.name} "
        }
        if (allergens != "") {
            restauAllergens?.text = "Allergens : $allergens"
        } else {
            restauAllergens?.text = resources?.getString(R.string.noAllergens)
        }

        var characs = ""
        restaurant?.characteristics?.forEach {
            characs += "${it.name} "
        }
        if (characs != "") {
            restauCharacs?.text = "Characteristics : $characs"
        } else {
            restauCharacs?.text = resources?.getString(R.string.noCharacs)
        }
        restauAddressTextView?.text = addressFull
        restauWebsite?.text = restaurant?.website

        goToMapBtn?.setOnClickListener {
            this.lat?.let { it1 ->
                lng?.let { it2 ->
                    restaurant?.let { it3 ->
                        MapsActivity.navigateTo(
                            this,
                            it3, it1, it2
                        )
                    }
                }
            }
        }

        GeocoderRepository.retrieveLocationFromAddress(addressFull, object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("toto", call.request().url().toString())
                Log.d("toto", "Error : ${t.message}")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val json = response.body()
                val location =
                    json?.get("results")?.asJsonArray?.get(0)?.asJsonObject?.getAsJsonObject("geometry")
                lat = location?.asJsonObject?.getAsJsonObject("location")?.get("lat")?.asDouble
                lng = location?.asJsonObject?.getAsJsonObject("location")?.get("lng")?.asDouble
                Log.d("toto", "Loc = $lat $lng")
            }
        })

    }
}