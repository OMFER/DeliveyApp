package com.omfer.deliveryapp.activities.client.home

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.omfer.deliveryapp.R
import com.omfer.deliveryapp.activities.MainActivity
import com.omfer.deliveryapp.models.User
import com.omfer.deliveryapp.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"
    private var buttonLogout: Button? = null
    private var sharedPref: SharedPref? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonLogout = findViewById(R.id.btn_logout)
        buttonLogout?.setOnClickListener { logOut() }
        //getUserFromSession()
    }
    private fun getUserFromSession(){
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {//si el usuarrio existe
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }
    private fun logOut(){
        Log.i(TAG, "Cerrar sesión")
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}