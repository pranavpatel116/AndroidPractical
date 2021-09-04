package com.dev.practical.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.practical.extra.DialogLoader
import com.dev.practical.extra.SessionManager
import kotlinx.android.synthetic.main.custom_tool_bar.*

open class BaseActivity : AppCompatActivity() {

    //CONTEXT
    lateinit var context: Context

    // SESSION MANAGER
    open lateinit var sessionManager: SessionManager

    // DIALOG LOADER
    open lateinit var dialogLoader: DialogLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INIT CONTEXT
        context = this

        // INIT SESSION MANAGER
        sessionManager = SessionManager.getInstance(context)!!

        // INIT DIALOG LOADER
        dialogLoader = DialogLoader(context)
    }

    // HIDE DRAWER MENU
    public fun hideDrawerMenu(){
        iv_drawer_menu.visibility = View.GONE
        iv_back.visibility = View.VISIBLE
    }

    // SHOW DRAWER MENU
    public fun showDrawerMenu(){
        iv_drawer_menu.visibility = View.VISIBLE
        iv_back.visibility = View.GONE
    }
}