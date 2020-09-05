package com.chidozie.n.aadpracticeproject.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.chidozie.n.aadpracticeproject.MainActivity
import com.chidozie.n.aadpracticeproject.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = MainActivity.newIntent(this)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        // 2 seconds
        private const val SPLASH_DELAY = 2_000L
    }

}
