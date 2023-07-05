package com.chen.agp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.chen.agp.db.entity.ProductEntity
import java.lang.IllegalArgumentException

class MyViewModel(ctx:Application):AndroidViewModel(ctx) {

    private val QUERY_KEY = "QUERY"

    private var mSavedStateHandler: SavedStateHandle? = null
    private var mProducts: LiveData<List<ProductEntity?>?>? = null

    constructor(
        application: Application,
        savedStateHandle: SavedStateHandle
    ):this(application) {
        mSavedStateHandler = savedStateHandle
        throw IllegalArgumentException("")
        // Use the savedStateHandle.getLiveData() as the input to switchMap,
        // allowing us to recalculate what LiveData to get from the DataRepository
        // based on what query the user has entered

    }

    fun setQuery(query: CharSequence?) {
        // Save the user's query into the SavedStateHandle.
        // This ensures that we retain the value across process death
        // and is used as the input into the Transformations.switchMap above
        mSavedStateHandler!!.set(QUERY_KEY, query)
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    fun getProducts(): LiveData<List<ProductEntity?>?>? {
        return mProducts
    }
}