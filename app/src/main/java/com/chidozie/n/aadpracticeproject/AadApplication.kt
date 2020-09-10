package com.chidozie.n.aadpracticeproject

import android.app.Application
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl

class AadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseImpl.inject(this)
    }
}
