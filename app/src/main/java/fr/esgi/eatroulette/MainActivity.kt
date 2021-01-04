package fr.esgi.eatroulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.eatroulette.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectBtn?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }
}