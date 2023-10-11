package com.chen.agp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A [ViewModelProvider.Factory] that provides dependencies to [CheeseViewModel],
 * allowing tests to switch out [CheeseDao] implementation via constructor injection.
 */
class AGPViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST") // Guaranteed to succeed at this point.
            return PagingViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
