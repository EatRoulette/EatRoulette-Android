package fr.esgi.eatroulette.connected.restaurants_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.eatroulette.R
import kotlinx.android.synthetic.main.activity_restaurant_list.*

class RestaurantListActivity : AppCompatActivity(),
    RestaurantViewHolder.OnRestaurantClickedListener {

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, RestaurantListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val restaurants = listOf(
        Restaurant("Enjoy Sushi", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Mc Donald", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Burger King", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Five Guys", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Crepe Party", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Pepper Joy", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("EatRoulette", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Trionfo", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("Flora Danica", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("L'ins Temps Dej", "3 rue Victor Hugo", "PARIS", "75008"),
        Restaurant("VeganMiam", "3 rue Victor Hugo", "PARIS", "75008"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        restaurantList?.apply {
            layoutManager = LinearLayoutManager(this@RestaurantListActivity)
            adapter = RestaurantAdapter(restaurants, this@RestaurantListActivity)
        }

    }

    override fun onRestaurantClicked(restaurant: Restaurant?) {
        Log.d("InRestaurantList", restaurant.toString())
    }
}