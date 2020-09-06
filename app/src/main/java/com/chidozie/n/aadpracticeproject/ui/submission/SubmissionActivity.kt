package com.chidozie.n.aadpracticeproject.ui.submission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ActivitySubmissionBinding
import com.chidozie.n.aadpracticeproject.databinding.ToolbarSubmissionBinding
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.ConfirmSubmitDialog
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.SubmissionResultDialog
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel.ConfirmSubmitViewModel
import com.chidozie.n.aadpracticeproject.ui.util.CustomActionBarActivity
import kotlin.random.Random

class SubmissionActivity : CustomActionBarActivity() {

    private val confirmViewModel: ConfirmSubmitViewModel by viewModels()

    override fun onCreate(
        savedInstanceState: Bundle?, actionBar: ActionBar, inflater: LayoutInflater
    ) {
        val binding: ActivitySubmissionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_submission)

        val viewGroup = binding.root as? ViewGroup
        val toolbarBinding = ToolbarSubmissionBinding.inflate(inflater, viewGroup, false)

        // display custom action bar
        actionBar.customView = toolbarBinding.root

        toolbarBinding.backImageView.setOnClickListener {
            finish()
        }

        binding.submitButton.setOnClickListener {
            // todo: check that fields are complete
            ConfirmSubmitDialog.newInstance().show(supportFragmentManager, null)
            // todo: if submission is successful, empty all the fields
        }

        confirmViewModel.observeConfirmClicked.observe(this) {
            SubmissionResultDialog.newInstance(Random.nextBoolean())
                .show(supportFragmentManager, null)
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SubmissionActivity::class.java)
        }
    }

}
