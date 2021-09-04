package com.dev.practical.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.practical.R
import com.dev.practical.adapter.DrawerListAdapter
import com.dev.practical.databinding.ActivityMainBinding
import com.dev.practical.extra.Utils
import com.dev.practical.model.DrawerMenuModel
import com.dev.practical.model.TaskModel
import com.google.android.material.navigation.NavigationView
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.custom_tool_bar.*

class MainActivity : BaseActivity(), DrawerListAdapter.OnDrawerItemSelectListener{

    // BINDING
    lateinit var binding : ActivityMainBinding

    // STATIC VARIABLES
    companion object {
        lateinit var drawerToggle: ActionBarDrawerToggle
        lateinit var drawerLayout: DrawerLayout
        lateinit var toolbar: Toolbar
        lateinit var ivDrawer: ImageView
    }

    // BOOLEAN
    var isDrawerOpened : Boolean = false
    var doublePressedBackToExit = false

    // ADAPTER
    private lateinit var drawerListAdapter: DrawerListAdapter

    // ARRAY LIST
    private var drawerList: ArrayList<DrawerMenuModel> = ArrayList()
    var onGoingModels : ArrayList<TaskModel> = ArrayList()
    var firebaseTasksIds: ArrayList<String> = ArrayList()

    // IMAGE LOADER
    private val imageLoader: ImageLoader = ImageLoader.getInstance()
    private val options: DisplayImageOptions = DisplayImageOptions.Builder()
        .showImageForEmptyUri(R.drawable.place_holder)
        .showImageOnLoading(R.drawable.place_holder)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .imageScaleType(ImageScaleType.EXACTLY)
        .considerExifParams(true)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // INIT CONTEXT
        context = this

        init()
    }

    private fun init() {
        // INIT TOOL BAR
        initToolBar()

        // HIDE DRAWER MENU
        showDrawerMenu()

        setDrawerMenu()

        //showTripAcceptDialog()
        tx_tool_bar_header.text = resources.getString(R.string.text_main)

        // INTI ADD TASK
        initAddTaskClick()

    }

    override fun onResume() {
        super.onResume()
        setHeaderData()
    }

    // INIT ADD TASK CLICK
    private fun initAddTaskClick(){
        iv_add.setOnClickListener {
            val addTask = Intent(context, AddTaskActivity :: class.java)
            //haddTask.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(addTask)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHeaderData() {
        val navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val navUserProfile = headerView.findViewById<View>(R.id.iv_user_profile) as ImageView
        val userName = headerView.findViewById<View>(R.id.tv_user_name) as TextView

        if (sessionManager.name != "") {
            userName.text = sessionManager.name
        }

        if (sessionManager.profilePic != null){
            imageLoader.displayImage(sessionManager.profilePic.toString(), navUserProfile, options)
        }

    }


    // INIT TOOL BAR
    private fun initToolBar(){
        // INIT DRAWER LAYOUT
        drawerLayout = findViewById(R.id.drawer_layout)

        ivDrawer = findViewById(R.id.iv_drawer_menu)

        // INIT TOOL BAR
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // INIT DRAWER HOME BUTTON AND REMOVE DEFAULT TITTLE
        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }

        // DRAWER TOGGLE SET UP
        drawerToggle = setUpDrawerToggle()
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        ivDrawer.setOnClickListener {
            if (isDrawerOpened){
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }

    // SET UP DRAWER
    private fun setUpDrawerToggle(): ActionBarDrawerToggle {
        return object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
                isDrawerOpened = false

            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
                isDrawerOpened = true
            }
        }
    }

    private fun setDrawerMenu() {
        // INIT RECYCLER VIEW
        binding.recDrawerView.setHasFixedSize(true)
        binding.recDrawerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // INIT ARRAY LIST
        drawerList = ArrayList()
        drawerList.add(
            0, DrawerMenuModel(0, getString(R.string.text_profile),0)
        )
        drawerList.add(
            1, DrawerMenuModel(1, getString(R.string.text_add_task),0)
        )
        drawerList.add(
            2, DrawerMenuModel(2, getString(R.string.text_lorem_ipsum),0)
        )
        drawerList.add(
            3, DrawerMenuModel(3, getString(R.string.text_lorem_ipsum), 0)
        )
        drawerList.add(
            4, DrawerMenuModel(4, getString(R.string.text_logout), 0)
        )

        drawerListAdapter = DrawerListAdapter(context, drawerList, this)
        binding.recDrawerView.adapter = drawerListAdapter

    }

    override fun onDrawerItemSelect(pos: Int) {
        drawerLayout.closeDrawer(GravityCompat.START)
        Utils.hideSoftKeyboard(this)
        if (pos == 0){
            val profile : Intent = Intent(context, MyProfileActivity ::class.java)
            profile.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(profile)
        } else if (pos == 1){
            val addTask : Intent = Intent(context, AddTaskActivity ::class.java)
            addTask.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(addTask)
        } else if (pos == 4){
            showLogoutPopup()
        }
    }

    // SHOW CANCEL RIDE POPUP
    private fun showLogoutPopup(){
        try {
            val alertCancelBooking = AppCompatDialog(context)
            alertCancelBooking.setContentView(R.layout.confirmation_pop_up)

            val txNo = alertCancelBooking.findViewById<AppCompatTextView>(R.id.btn_no)
            val txYes = alertCancelBooking.findViewById<AppCompatTextView>(R.id.btn_yes)
            val txMessage = alertCancelBooking.findViewById<AppCompatTextView>(R.id.tx_message)

            txMessage!!.text = context.resources.getString(R.string.logout_str)

            txNo!!.setOnClickListener {
                alertCancelBooking.dismiss()
            }

            txYes!!.setOnClickListener {
                sessionManager.clearData()
                val login = Intent(context, LoginActivity::class.java)
                login.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(login)
                alertCancelBooking.dismiss()
            }

            alertCancelBooking.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
            alertCancelBooking.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            alertCancelBooking.window!!.setGravity(Gravity.CENTER)
            alertCancelBooking.show()
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

}