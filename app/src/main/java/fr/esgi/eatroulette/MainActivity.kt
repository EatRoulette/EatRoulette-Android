package fr.esgi.eatroulette

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.esgi.eatroulette.connected.home_page.HomePageActivity
import fr.esgi.eatroulette.not_connected.login.LoginActivity
import fr.esgi.eatroulette.not_connected.register.RegisterActivity
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!Util.isOnline()) {
            connectionIssue?.text = resources?.getString(R.string.errorNoConnection)
        } else {
            connectionIssue?.text = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this@MainActivity.getPreferences(Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString(getString(R.string.token), "")
        if(token !== null && token != "" ){
            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            this@MainActivity.startActivity(intent)
        }

        if (!Util.isOnline()) {
            connectionIssue?.text = resources?.getString(R.string.errorNoConnection)
        }

        setContentView(R.layout.activity_main)
        registerBtn?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            this.startActivity(intent)
        }
        connectBtn?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}