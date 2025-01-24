package com.omfer.deliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.omfer.deliveryapp.R
import com.omfer.deliveryapp.activities.client.home.ClientHomeActivity
import com.omfer.deliveryapp.models.ResponseHTTP
import com.omfer.deliveryapp.models.User
import com.omfer.deliveryapp.providers.UsersProvider
import com.omfer.deliveryapp.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var imageViewGoToRegister: ImageView? = null
    private var editTextEmail: EditText? = null
    private var editTextPass: EditText? = null
    private var buttonLogin: Button? = null
    private var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)
        editTextEmail = findViewById(R.id.login_email)
        editTextPass = findViewById(R.id.login_pass)
        buttonLogin = findViewById(R.id.login_btn)

        buttonLogin?.setOnClickListener { logIn()}
        imageViewGoToRegister?.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        getUserFromSession()
    }
    private fun logIn() {
        Log.i(TAG, "Iniciar sesión")
        val email = editTextEmail?.text.toString()
        val pass = editTextPass?.text.toString()
        if (validate(email, pass)) {

            usersProvider.login(email, pass)?.enqueue(object : Callback<ResponseHTTP>{
                override fun onResponse( call: Call<ResponseHTTP>, response: Response<ResponseHTTP> ) {
                    Toast.makeText(this@MainActivity, "${response.body()?.message}", Toast.LENGTH_LONG).show()
                    if (response.body()?.isSuccess == true) {
                        Toast.makeText(this@MainActivity, "${response.body()?.message}", Toast.LENGTH_LONG).show()
                        saveUserInSession(response.body()?.data.toString())
                        goToClientHome()
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ResponseHTTP>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Se produjo un error ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        } else { Toast.makeText(this, "Login incorrecto", Toast.LENGTH_LONG).show() }
    }

    private fun goToClientHome(){
        Log.i(TAG, "Ir a Home")
        val i = Intent(this, ClientHomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun saveUserInSession(data: String) {
        Log.i(TAG, "Guardar usuario en sesión")
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    private fun String.isEmailValid(): Boolean {
        Log.i(TAG, "Validar email")
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun getUserFromSession(){
        Log.i(TAG, "Obtener usuario de sesión")
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {//si el usuarrio existe
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            //goToClientHome()
        }
    }

    private fun validate(email: String, pass: String): Boolean {
        Log.i(TAG, "Validar campos")
        //return !(email.isEmpty() || pass.isEmpty())
        if (email.isEmpty() || pass.isEmpty()) {
            return false
        }
        if (!email.isEmailValid()) {
            return false
        }
        return true
    }
}