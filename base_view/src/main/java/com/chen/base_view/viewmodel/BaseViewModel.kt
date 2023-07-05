package com.chen.base_view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 * 针对所有baseActivity的baseViewModel
 *
 */
open class BaseViewModel(application:Application):AndroidViewModel(application) {
    override fun onCleared() {
        super.onCleared()
    }

}