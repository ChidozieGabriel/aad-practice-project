package com.chidozie.n.aadpracticeproject.ui.submission.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.chidozie.n.aadpracticeproject.databinding.DialogConfirmSubmitBinding
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel.ConfirmSubmitViewModel
import com.chidozie.n.aadpracticeproject.ui.util.BaseDialog

class ConfirmSubmitDialog : BaseDialog() {

    override lateinit var binding: DialogConfirmSubmitBinding

    private val viewModel: ConfirmSubmitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DialogConfirmSubmitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.cancelImageView.setOnClickListener {
            dismiss()
        }

        binding.yesButton.setOnClickListener {
            viewModel.onConfirmClicked()
            dismiss()
        }
    }

    companion object {
        fun newInstance(): ConfirmSubmitDialog {
            return ConfirmSubmitDialog()
        }
    }

}
