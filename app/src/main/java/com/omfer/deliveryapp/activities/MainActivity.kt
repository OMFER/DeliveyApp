package com.omfer.deliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.omfer.deliveryapp.R

class MainActivity : AppCompatActivity() {

    private var imageViewGoToRegister: ImageView? = null
    private var editTextEmail: EditText? = null
    private var editTextPass: EditText? = null
    private var buttonLogin: Button? = null

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
    }
    private fun logIn() {
        val email = editTextEmail?.text.toString()
        val pass = editTextPass?.text.toString()
        if (validate(email, pass)) {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Login incorrecto", Toast.LENGTH_LONG).show()
        }
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun validate(email: String, pass: String): Boolean {
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