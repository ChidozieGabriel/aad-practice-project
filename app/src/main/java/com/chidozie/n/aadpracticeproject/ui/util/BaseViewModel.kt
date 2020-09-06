package com.chidozie.n.aadpracticeproject.ui.util

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chidozie.n.aadpracticeproject.R
import kotlinx.coroutines.launch
import java.io.IOException

abstract class BaseViewModel : ViewModel() {

    private val showToast = MutableLiveData<Int>()
    val observeShowToast: LiveData<Int>
        get() = showToast

    protected fun runApi(
        onError: (Exception) -> Unit = {},
        finally: () -> Unit = {},
        apiCall: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                apiCall()

            } catch (e: Exception) {
                onError(e)

                if (e is IOException) {
                    showToast(R.string.no_internet_connection)
                }

            } finally {
                finally()
            }
        }
    }

    protected fun showToast(@StringRes res: Int) {
        showToast.postValue(res)
    }

}
