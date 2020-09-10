package com.chidozie.n.aadpracticeproject.ui.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

@Suppress("unused")
class MyTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader, className: String, context: Context
    ): Application {
        return super.newApplication(cl, MockApplication::class.java.name, context)
    }
}
