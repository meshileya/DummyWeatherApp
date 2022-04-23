package com.dummy.dummyweatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dummy.dummyweatherapp.R

val SPLASH_DISPLAY_LENGTH = 2000

class SplashScreenActivity : AppCompatActivity() {

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_splashscreen)
        Handler()
            .postDelayed({ initData() }, SPLASH_DISPLAY_LENGTH.toLong())
    }


    fun initData() {
        val mainIntent = Intent(this@SplashScreenActivity, UserHomeActivity::class.java)
        this@SplashScreenActivity.startActivity(mainIntent)
        finish()
    }
}