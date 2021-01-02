package fr.esgi.eatroulette.connected.friend.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.eatroulette.connected.friend.User

class FriendAdapter(
    private val friends: List<User>,
    private val onUserClickedListener: FriendViewHolder.OnUserClickedListener
) : RecyclerView.Adapter<FriendViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FriendViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friends[position]
        holder.bind(friend, onUserClickedListener)
    }

    override fun getItemCount(): Int = friends.size

}