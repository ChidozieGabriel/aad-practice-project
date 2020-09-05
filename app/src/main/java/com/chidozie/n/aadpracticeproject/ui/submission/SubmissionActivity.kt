package com.chidozie.n.aadpracticeproject.ui.submission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ActivitySubmissionBinding

class SubmissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySubmissionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_submission)

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SubmissionActivity::class.java)
        }
    }

}
