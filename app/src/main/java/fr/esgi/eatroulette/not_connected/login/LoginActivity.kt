package fr.esgi.eatroulette.not_connected.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.connected.home_page.HomePageActivity
import fr.esgi.eatroulette.infrastructure.eatroulette.EatRouletteRepository
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //if (Util.isOnline()) {
            validate?.setOnClickListener {
                val emailStr: String = email.text.toString();
                val passwordStr: String = password.text.toString();
                // todo manage error with error message (regex + null and empty)
                // todo else  :
                login(emailStr, passwordStr);
            }
        /*} else {
            email?.isVisible = false
            password?.isVisible = false
            validate?.isVisible = false
            errorMessage?.text = resources?.getString(R.string.errorNoConnection)
        }*/

    }

    private fun login(email: String, password: String) {;
        EatRouletteRepository.login(email, password, object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.text = this@LoginActivity.getText(R.string.errorMessage);
                Log.d("eatRoll-login", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d(
                    "eatRoll-login",
                    "Code ${response.code()}, body = Token(XXXX), message = ${response.message()}"
                )
                val body: LoginResponse? = response.body()
                if (response.code() != 401) {
                    if (body != null) {
                        val sharedPref = this@LoginActivity.getPreferences(Context.MODE_PRIVATE) ?: return
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