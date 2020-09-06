package com.chidozie.n.aadpracticeproject.ui.submission.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ActivitySubmissionBinding
import com.chidozie.n.aadpracticeproject.databinding.ToolbarSubmissionBinding
import com.chidozie.n.aadpracticeproject.ui.submission.activity.viewmodel.SubmissionViewModel
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.ConfirmSubmitDialog
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.SubmissionResultDialog
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel.ConfirmSubmitViewModel
import com.chidozie.n.aadpracticeproject.ui.util.BaseActivity

class SubmissionActivity : BaseActivity() {

    override val viewModel: SubmissionViewModel by viewModels()

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

        binding.projectLinkEditText.setOnEditorActionListener { _, actionId, keyEvent: KeyEvent? ->
            val isEnterClicked = keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER
            val actionDone = actionId == EditorInfo.IME_ACTION_DONE

            if (isEnterClicked || actionDone) {
                binding.submitButton.performClick()
            }

            false
        }

        binding.submitButton.setOnClickListener {
            viewModel.onSubmitClicked(
                binding.firstNameEditText.text?.toString(),
                binding.lastNameEditText.text?.toString(),
                binding.emailAddressEditText.text?.toString(),
                binding.projectLinkEditText.text?.toString()
            )
        }

        viewModel.observeShowConfirmDialog.observe(this) {
            ConfirmSubmitDialog.newInstance().show(supportFragmentManager, null)
        }

        viewModel.observeShowLoading.observe(this) { isLoading ->
            binding.loadingLayout.isVisible = isLoading
        }

        viewModel.observeClearAllFields.observe(this) {
            val fields = listOf(
                binding.firstNameEditText,
                binding.lastNameEditText,
                binding.emailAddressEditText,
                binding.projectLinkEditText
            )

            fields.forEach { field ->
                field.setText("")
            }
        }

        viewModel.observeSubmissionSuccessful.observe(this) { successful ->
            SubmissionResultDialog.newInstance(successful).show(supportFragmentManager, null)
        }

        confirmViewModel.observeConfirmClicked.observe(this) {
            viewModel.onConfirmClicked(
                binding.firstNameEditText.text?.toString(),
                binding.lastNameEditText.text?.toString(),
                binding.emailAddressEditText.text?.toString(),
                binding.projectLinkEditText.text?.toString()
            )
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SubmissionActivity::class.java)
        }
    }

}
