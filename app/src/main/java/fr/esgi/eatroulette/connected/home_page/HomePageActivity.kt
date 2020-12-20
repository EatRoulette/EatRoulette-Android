package fr.esgi.eatroulette.connected.home_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.list.RestaurantListActivity
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        restaurantListBtn?.setOnClickListener {
            RestaurantListActivity.navigateTo(this)
        }
    }
}