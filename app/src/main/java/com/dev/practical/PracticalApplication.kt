package com.dev.practical

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.view.WindowManager
import com.google.firebase.FirebaseApp
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType

class PracticalApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this

        FirebaseApp.initializeApp(this)

        // FOR IMAGE LOADER LIBRARY
        initImageLoader(applicationContext)
    }

    private fun initImageLoader(context: Context) {
        val config: ImageLoaderConfiguration.Builder = ImageLoaderConfiguration.Builder(context)
        config.threadPriority(Thread.NORM_PRIORITY - 2)
        config.denyCacheImageMultipleSizesInMemory()
        config.diskCacheFileNameGenerator(Md5FileNameGenerator())
        config.diskCacheSize(50 * 1024 * 1024) // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO)
        config.writeDebugLogs() // Remove for release app

        // Initialize ImageLoader with configuration.
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build())
    }

    companion object {

        var instance: Application? = null
            private set

        val TAG: String = PracticalApplication ::class.java.simpleName

        fun adjustFontScale(context: Context, configuration: Configuration, scale: Float) {
            configuration.fontScale = scale
            val metrics = context.resources.displayMetrics
            val wm = context.getSystemService(WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            context.resources.updateConfiguration(configuration, metrics)
        }


    }
}