package com.omfer.deliveryapp.activities

import android.content.Intent
import android.os.Bundle
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

    var imageViewGoToLogin: ImageView? = null
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextPhone: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPass: EditText? = null
    var editTextPassConf: EditText? = null
    var buttonReg: Button? = null

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
            val i = Intent(this, RegisterActivity::class.java)
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

        if (name.isEmpty() || ape.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || passConf.isEmpty()) {
            return
        }
        if (pass != passConf) {
            return
        }
        Toast.makeText(this, "Registro correcto", Toast.LENGTH_LONG).show()
    }
}