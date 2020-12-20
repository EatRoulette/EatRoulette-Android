package fr.esgi.eatroulette.connected.restaurants_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private val restaurants: List<Restaurant>,
    private val onRestaurantClickedListener: RestaurantViewHolder.OnRestaurantClickedListener
) : RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant, onRestaurantClickedListener);
    }

    override fun getItemCount(): Int = restaurants.size
}