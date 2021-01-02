package fr.esgi.eatroulette.connected.friend.list

import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.friend.User

class FriendViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.user_item, parent, false)),
    View.OnClickListener {

    interface OnUserClickedListener {
        fun onUserClicked(user: User?)
    }

    private var onUserClickedListener: OnUserClickedListener? = null
    private var user: User? = null
    private var userNameTextView: TextView? = null
    private var userEmailTextView: TextView? = null

    init {
        userNameTextView = itemView.findViewById(R.id.userNameTextView)
        userEmailTextView = itemView.findViewById(R.id.userEmailTextView)
    }

    fun bind(user: User, onUserClickedListener: OnUserClickedListener) {
        this.user = user
        this.onUserClickedListener = onUserClickedListener
        userNameTextView?.text = "${user.firstName} ${user.lastName}"
        userEmailTextView?.text = user.email

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onUserClickedListener?.onUserClicked(user)
    }

}
