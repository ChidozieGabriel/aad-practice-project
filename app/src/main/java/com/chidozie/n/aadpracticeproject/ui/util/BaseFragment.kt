package com.chidozie.n.aadpracticeproject.ui.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.chidozie.n.aadpracticeproject.extension.showToast

abstract class BaseFragment : Fragment() {

    protected open val viewModel: BaseViewModel? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel?.observeShowToast?.observe(viewLifecycleOwner) { res ->
            requireContext().showToast(res)
        }
    }
}
