package fr.esgi.eatroulette.connected.friend.list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.friend.User
import kotlinx.android.synthetic.main.activity_friend_list.*

class FriendListActivity : AppCompatActivity(), FriendViewHolder.OnUserClickedListener {

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, FriendListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val friends = listOf<User>(
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com"),
        User("John", "Doe", "john.doe@email.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        friendList?.apply {
            layoutManager = LinearLayoutManager(this@FriendListActivity)
            adapter = friends?.let { FriendAdapter(it, this@FriendListActivity) }
        }
    }

    override fun onUserClicked(user: User?) {
        Log.d("toto", user.toString())
    }
}