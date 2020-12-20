package fr.esgi.eatroulette.connected.restaurant.list

import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.restaurant.Restaurant

class RestaurantViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.restaurant_item, parent, false)),
    View.OnClickListener {

    interface OnRestaurantClickedListener {
        fun onRestaurantClicked(restaurant: Restaurant?)
    }

    private var onRestaurantClickedListener: OnRestaurantClickedListener? = null
    private var restaurant: Restaurant? = null
    private var restaurantNameTextView: TextView? = null
    private var restaurantAddressTextView: TextView? = null

    init {
        restaurantNameTextView = itemView.findViewById(R.id.restaurantNameTextView)
        restaurantAddressTextView = itemView.findViewById(R.id.restaurantAddressTextView)
    }


    fun bind(restaurant: Restaurant, onRestaurantClickedListener: OnRestaurantClickedListener) {
        this.restaurant = restaurant
        this.onRestaurantClickedListener = onRestaurantClickedListener
        restaurantNameTextView?.text = restaurant.name
        restaurantAddressTextView?.text = restaurant.address

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onRestaurantClickedListener?.onRestaurantClicked(restaurant)
    }

}