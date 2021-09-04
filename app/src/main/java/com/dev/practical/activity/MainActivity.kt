package com.dev.practical.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.practical.R
import com.dev.practical.adapter.DrawerListAdapter
import com.dev.practical.databinding.ActivityMainBinding
import com.dev.practical.model.DrawerMenuModel
import com.google.android.material.navigation.NavigationView
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.ImageScaleType
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

    }

    override fun onResume() {
        super.onResume()
        setHeaderData()
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
            1, DrawerMenuModel(1, getString(R.string.text_lorem_ipsum),0)
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

    }

}