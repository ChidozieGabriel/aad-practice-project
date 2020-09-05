package com.chidozie.n.aadpracticeproject.ui.util

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.chidozie.n.aadpracticeproject.R

abstract class CustomActionBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar

        if (actionBar == null) {
            Toast.makeText(this, R.string.no_actionbar, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        val inflater = LayoutInflater.from(this)

        onCreate(savedInstanceState, actionBar, inflater)
    }

    abstract fun onCreate(
        savedInstanceState: Bundle?, actionBar: ActionBar, inflater: LayoutInflater
    )

}
