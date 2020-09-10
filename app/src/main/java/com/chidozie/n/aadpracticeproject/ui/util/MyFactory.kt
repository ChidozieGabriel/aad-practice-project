package com.chidozie.n.aadpracticeproject.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * This object enables the creation of a mock factory for instrumentation tests
 */
@Suppress("UNUSED_PARAMETER")
object MyFactory {
    fun <T : ViewModel> getFactory(viewModel: KClass<T>): ViewModelProvider.Factory? {
        return null
    }
}
