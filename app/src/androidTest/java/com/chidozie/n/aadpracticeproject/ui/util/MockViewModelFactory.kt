package com.chidozie.n.aadpracticeproject.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object MockViewModelFactory {

    fun <T : ViewModel> createViewModelFor(model: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }

}
