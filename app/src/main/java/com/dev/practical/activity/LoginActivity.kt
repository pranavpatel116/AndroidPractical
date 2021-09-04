package com.dev.practical.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dev.practical.R
import com.dev.practical.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    // BINDING
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // INIT SIGN UP CLICK
        initSignupClick()

    }

    // INIT SIGN UP CLICK
    private fun initSignupClick(){
        binding.tvSignUp.setOnClickListener {
            val signup = Intent(context, RegisterActivity :: class.java)
            startActivity(signup)
        }
    }
}