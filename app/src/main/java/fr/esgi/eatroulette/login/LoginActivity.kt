package fr.esgi.eatroulette.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.home_page.HomePageActivity
import fr.esgi.eatroulette.infrastructure.eatroulette.RestaurantRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validate?.setOnClickListener {
            val emailStr: String = email.text.toString();
            val passwordStr: String = password.text.toString();
            // todo manage error with error message (regex + null and empty)
            // todo else  :
            login(emailStr, passwordStr);
        }
    }

    private fun login(email: String, password: String) {;
        RestaurantRepository.login(email, password, object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.text = R.string.errorMessage.toString();
                Log.d("toto", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d(
                    "toto",
                    "Code ${response.code()}, body = ${response.body()}, message = ${response.message()}"
                )
                val body: LoginResponse? = response.body()
                if (response.code() != 401) {
                    if (body != null) {
                        val sharedPref =
                            this@LoginActivity.getPreferences(Context.MODE_PRIVATE) ?: return
                        with(sharedPref.edit()) {
                            putString(getString(R.string.token), body.token)
                            apply()
                            val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                            this@LoginActivity.startActivity(intent)
                        }
                    } else {
                        errorMessage.text = this@LoginActivity.getText(R.string.errorLoginMessage);
                    }
                } else {
                    errorMessage.text = this@LoginActivity.getText(R.string.errorLoginMessage);
                }
            }
        })
    }
}