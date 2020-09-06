package com.chidozie.n.aadpracticeproject.ui.util

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.extension.showToast

abstract class BaseActivity : AppCompatActivity() {

    protected open val viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar

        if (actionBar == null) {
            showToast(R.string.no_actionbar)
            finish()
            return
        }

        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        val inflater = LayoutInflater.from(this)

        onCreate(savedInstanceState, actionBar, inflater)

        viewModel?.observeShowToast?.observe(this) { res ->
            showToast(res)
        }
    }

    abstract fun onCreate(
        savedInstanceState: Bundle?, actionBar: ActionBar, inflater: LayoutInflater
    )

}
