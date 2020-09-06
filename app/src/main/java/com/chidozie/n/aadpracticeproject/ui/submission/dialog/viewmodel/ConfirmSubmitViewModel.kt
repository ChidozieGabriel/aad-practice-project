package com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmSubmitViewModel : ViewModel() {

    private val confirmClicked = MutableLiveData<Unit>()
    val observeConfirmClicked: LiveData<Unit>
        get() = confirmClicked

    fun onConfirmClicked() {
        confirmClicked.postValue(Unit)
    }
}
