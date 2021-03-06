package fr.esgi.eatroulette.connected.restaurant.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.eatroulette.MainActivity
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.restaurant.detail.RestaurantDetailActivity
import fr.esgi.eatroulette.infrastructure.eatroulette.EatRouletteRepository
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantListActivity : AppCompatActivity(),
    RestaurantViewHolder.OnRestaurantClickedListener {

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, RestaurantListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var restaurants: List<Restaurant>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        if (!Util.isOnline()) {
            MainActivity.navigateTo(this)
        }

        loadData()

        pullToRefresh.setOnRefreshListener {
            loadData()
            pullToRefresh.isRefreshing = false
        }

    }

    override fun onRestaurantClicked(restaurant: Restaurant?) {
        restaurant?.let { RestaurantDetailActivity.navigateTo(this, it) }
    }

    private fun loadData() {
        EatRouletteRepository.retrieveAllRestaurant(object : Callback<List<Restaurant>> {
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.d("eatRoll-restaurantlist", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                restaurants = response.body()
                Log.d("eatRoll-restaurantlist", "Code ${response.code()}, Restaurants = ${response.body()}")
                restaurantList?.apply {
                    layoutManager = LinearLayoutManager(this@RestaurantListActivity)
                    adapter =
                        restaurants?.let { RestaurantAdapter(it, this@RestaurantListActivity) }
                }
                loader?.visibility = View.INVISIBLE
                restaurantList?.visibility = View.VISIBLE
            }
        })
    }
}