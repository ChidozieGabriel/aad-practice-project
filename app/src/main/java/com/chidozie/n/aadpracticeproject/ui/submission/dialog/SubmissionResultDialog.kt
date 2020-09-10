package com.chidozie.n.aadpracticeproject.ui.submission.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.DialogSubmissionResultBinding
import com.chidozie.n.aadpracticeproject.ui.util.BaseDialog

class SubmissionResultDialog : BaseDialog() {

    override lateinit var binding: DialogSubmissionResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DialogSubmissionResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val success = requireArguments().getBoolean(EXTRA_SUCCESS)

        val image: Int
        val text: Int
        if (success) {
            image = R.drawable.ic_check_success
            text = R.string.submission_successful
        } else {
            image = R.drawable.ic_check_failure
            text = R.string.submission_not_successful
        }

        binding.textView.text = getText(text)
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), image))
    }

    companion object {

        private const val EXTRA_SUCCESS = "EXTRA_SUCCESS"

        fun newInstance(success: Boolean): SubmissionResultDialog {
            return SubmissionResultDialog().apply {
                arguments = Bundle().apply {
                    putBoolean(EXTRA_SUCCESS, success)
                }
            }
        }
    }

}
