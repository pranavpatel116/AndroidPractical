package com.dev.practical.activity

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dev.practical.R
import com.dev.practical.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    // BINDING
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        // INIT HANDLER
        initHandler()
    }

    // INIT HANDLER
    private fun initHandler(){
        Handler(Looper.myLooper()!!).
        postDelayed(Runnable {
            if (sessionManager.isUserLoggedIn!!){
                val main = Intent(context, MainActivity :: class.java)
                main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(main)
                finish()
            } else {
                val login = Intent(context, LoginActivity :: class.java)
                login.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(login)
                finish()
            }
        }, 1000)
    }
}