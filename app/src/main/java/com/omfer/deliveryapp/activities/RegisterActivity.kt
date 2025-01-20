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

class RegisterActivity : AppCompatActivity() {

    private var imageViewGoToLogin: ImageView? = null
    private var editTextName: EditText? = null
    private var editTextLastName: EditText? = null
    private var editTextPhone: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextPass: EditText? = null
    private var editTextPassConf: EditText? = null
    private var buttonReg: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)
        editTextName = findViewById(R.id.reg_nomb)
        editTextLastName = findViewById(R.id.reg_ape)
        editTextPhone = findViewById(R.id.reg_tel)
        editTextEmail = findViewById(R.id.reg_email)
        editTextPass = findViewById(R.id.reg_pass)
        editTextPassConf = findViewById(R.id.reg_conf_pass)
        buttonReg = findViewById(R.id.reg_btn)


        buttonReg?.setOnClickListener { register() }
        imageViewGoToLogin?.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
    private fun register(){
        val name = editTextName?.text.toString()
        val ape = editTextLastName?.text.toString()
        val phone = editTextPhone?.text.toString()
        val email = editTextEmail?.text.toString()
        val pass = editTextPass?.text.toString()
        val passConf = editTextPassConf?.text.toString()

        if (validate(email, pass, passConf, name, ape, phone)) {
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Registro incorrecto", Toast.LENGTH_LONG).show()
        }
    }
    private fun validate(
        email: String,
        pass: String,
        passConf: String,
        name: String,
        ape: String,
        phone: String
    ): Boolean {
        if (name.isEmpty() || ape.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || passConf.isEmpty()) {
            return false
        }
        if (!email.isEmailValid()) {
            return false
        }
        if (pass != passConf) {
           Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}