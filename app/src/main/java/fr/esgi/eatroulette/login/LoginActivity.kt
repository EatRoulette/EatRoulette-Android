package fr.esgi.eatroulette.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.eatroulette.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validate?.setOnClickListener {
            // TODO call api to connect
        }
    }
}