package fr.esgi.eatroulette.register

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import fr.esgi.eatroulette.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.email
import kotlinx.android.synthetic.main.activity_register.errorMessage
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.validate

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
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

        if(password.text.toString() != passwordConfirmation.text.toString()){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.passwordsDoNotMatch);
        }
        if(Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongEmail);
        }
        if(isNumberOfGivenDigits(phone.text.toString(),10)){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongPhone);
        }
        if(isNumberOfGivenDigits(postalCode.text.toString(), 5)){
            hasValidate = false;
            errorMessage.text = this.getText(R.string.wrongPostalCode);
        }
        if (!hasFilledAllFields(fields)) {
            hasValidate = false;
            errorMessage.text = this.getText(R.string.fillEverything);
        }
        return hasValidate;
    }

    private fun isNumberOfGivenDigits(input: String, length: Int): Boolean{
        return input.isDigitsOnly() && input.length == length;
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
        // todo call register
    }
}