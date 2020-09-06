package com.chidozie.n.aadpracticeproject.ui.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatDialogFragment

abstract class BaseDialog : AppCompatDialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        )
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val params = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            attributes = params
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}
