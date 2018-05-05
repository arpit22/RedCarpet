package com.example.arpit.redcarpetapp

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

class FullSizePreview : AppCompatActivity() {


    internal var imgView: ImageView?= null
    internal var url_img: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fscreen)

        imgView = findViewById<View>(R.id.imView) as ImageView

        val data = intent.extras
        url_img = data!!.getString("url_string")

        Picasso.with(applicationContext)
                .load(url_img)
                .into(imgView)

        fullScreen()
    }

    fun fullScreen() {
        val uiOptions = window.decorView.systemUiVisibility
        var newUiOptions = uiOptions

        val isImmersiveModeEnabled = uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY == uiOptions
        if (isImmersiveModeEnabled) {
        } else {
        }
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }


        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        window.decorView.systemUiVisibility = newUiOptions

    }

}
