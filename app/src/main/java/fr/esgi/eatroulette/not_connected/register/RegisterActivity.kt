package fr.esgi.eatroulette.not_connected.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.eatroulette.MainActivity
import fr.esgi.eatroulette.R
import fr.esgi.eatroulette.infrastructure.eatroulette.RestaurantRepository
import fr.esgi.eatroulette.not_connected.login.LoginActivity
import fr.esgi.eatroulette.utils.Util
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.email
import kotlinx.android.synthetic.main.activity_register.errorMessage
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.validate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (!Util.isOnline()) {
            MainActivity.navigateTo(this)
        }

        validate.setOnClickListener {
            if (validateData()) {
                register()
            }
        }
    }

    private fun validateData(): Boolean {
        var hasValidate = true

        val fields = arrayOf(lastName.text.toString(),
            firstName.text.toString(),
            email.text.toString(),
            password.text.toString(),
            passwordConfirmation.text.toString(),
            address.text.toString(),
            city.text.toString(),
            postalCode.text.toString(),
            phone.text.toString())

        if(!isNumberOfGivenDigits(postalCode.text.toString(), 5)){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongPostalCode);
        }
        if(!isNumberOfGivenDigits(phone.text.toString(),10)){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongPhone);
        }
        if(password.text.toString() != passwordConfirmation.text.toString()){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.passwordsDoNotMatch);
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongEmail);
        }
        if (!hasFilledAllFields(fields)) {
            hasValidate = false;
            errorMessage.text = this.getText(R.string.fillEverything);
        }
        return hasValidate;
    }

    private fun isNumberOfGivenDigits(input: String, length: Int): Boolean{
        return input.toIntOrNull() != null && input.length == length;
    }

    private fun hasFilledAllFields(fields: Array<String>): Boolean {
        var hasFilled = true;
        for (field in fields) {
            if (TextUtils.isEmpty(field)){
                hasFilled = false
            }
        }
        return hasFilled
    }

    private fun register() {
        val data = Register(
            lastName.text.toString(), firstName.text.toString(), city.text.toString(),
            address.text.toString(), postalCode.text.toString(), phone.text.toString(),
            email.text.toString(), password.text.toString(), "user"
        )
        RestaurantRepository.register(data, object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                errorMessage.text = this@RegisterActivity.getText(R.string.errorMessage);
                Log.d("eatRoll-registry", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.d(
                    "eatRoll-registry",
                    "Code ${response.code()}, body = Token(XXXXXX), message = ${response.message()}"
                )
                val body: RegisterResponse? = response.body()
                if (response.code() != 409) {
                    if (body != null) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        this@RegisterActivity.startActivity(intent)
                    } else {
                        errorMessage.text = this@RegisterActivity.getText(R.string.errorRegisterMessage);
                    }
                } else {
                    errorMessage.text = this@RegisterActivity.getText(R.string.errorRegisterMessage);
                }
            }
        })
    }
}