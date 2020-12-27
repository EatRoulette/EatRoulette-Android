package fr.esgi.eatroulette.connected.restaurant.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.restaurant.list.RestaurantListActivity
import fr.esgi.eatroulette.infrastructure.ApiRepository
import fr.esgi.eatroulette.infrastructure.User
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        restaurant = intent?.getSerializableExtra("restaurant") as Restaurant?

        restauNameTextView?.text = restaurant?.name
        restauAddressTextView?.text =
            "${restaurant?.address} - ${restaurant?.city} ${restaurant?.postalCode}"
    }



}