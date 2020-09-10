package com.chidozie.n.aadpracticeproject.ui.util

import android.app.Application
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl

class MockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseImpl.mock(this)
    }
}
