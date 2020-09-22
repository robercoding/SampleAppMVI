package com.rober.sampleappmvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rober.sampleappmvi.data.api.RestApi
import com.rober.sampleappmvi.data.repository.MainRepository

class MainViewModelFactory(private val restApi: RestApi) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}