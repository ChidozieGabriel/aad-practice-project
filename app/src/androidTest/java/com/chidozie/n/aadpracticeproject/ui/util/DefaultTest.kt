package com.chidozie.n.aadpracticeproject.ui.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Rule

abstract class DefaultTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    companion object {

        @AfterClass
        @JvmStatic
        fun afterClass() {
            unmockkAll()
        }
    }
}
