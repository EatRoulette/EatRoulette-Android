package fr.esgi.eatroulette.connected.restaurant.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.Restaurant
import fr.esgi.eatroulette.connected.restaurant.Type
import fr.esgi.eatroulette.connected.restaurant.detail.RestaurantDetailActivity
import fr.esgi.eatroulette.infrastructure.ApiRepository
import fr.esgi.eatroulette.infrastructure.User
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


    private val typeList = listOf(
        Type("toto"),
        Type("toto")

    )

    private var restaurants:List<Restaurant>? = listOf(
        Restaurant("Enjoy Sushi",  typeList,"3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Mc Donald", typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Burger King", typeList,"3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Five Guys", typeList,"3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Crepe Party",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Pepper Joy",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("EatRoulette",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Trionfo",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Flora Danica",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("L'ins Temps Dej",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("VeganMiam",typeList, "3 rue Victor Hugo", "PARIS", "75008"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        ApiRepository.retrieveAllRestaurant(object: Callback<List<Restaurant>> {
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.d("toto", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                restaurants = response?.body()
                Log.d("toto", "Code ${response.code()}, Restaurants = ${response.body()}")
                restaurantList?.apply {
                    layoutManager = LinearLayoutManager(this@RestaurantListActivity)
                    adapter = restaurants?.let { RestaurantAdapter(it, this@RestaurantListActivity) }
                }
            }

        })


    }

    override fun onRestaurantClicked(restaurant: Restaurant?) {
        restaurant?.let { RestaurantDetailActivity.navigateTo(this, it) }
    }
}