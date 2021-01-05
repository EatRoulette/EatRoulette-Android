package fr.esgi.eatroulette.connected.home_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.eatroulette.MainActivity
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.friend.list.FriendListActivity
import fr.esgi.eatroulette.connected.restaurant.list.RestaurantListActivity
import fr.esgi.eatroulette.connected.roll.RollActivity
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        if (!Util.isOnline()) {
            MainActivity.navigateTo(this)
        }

        restaurantListBtn?.setOnClickListener {
            RestaurantListActivity.navigateTo(this)
        }

        rollBtn?.setOnClickListener {
            RollActivity.navigateTo(this)
        }

        friendListBtn?.setOnClickListener {
            FriendListActivity.navigateTo(this)
        }

    }
}