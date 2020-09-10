package com.chidozie.n.aadpracticeproject.extension

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chidozie.n.aadpracticeproject.ui.util.MyFactory
import kotlin.reflect.KClass

fun ComponentActivity.factory(viewModel: KClass<out ViewModel>): ViewModelProvider.Factory {
    return MyFactory.getFactory(viewModel) ?: defaultViewModelProviderFactory
}
