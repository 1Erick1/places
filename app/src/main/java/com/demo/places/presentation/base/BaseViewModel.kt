package com.demo.places.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Exception>()
    val empty = MutableLiveData<Boolean>()

    fun execute(func: suspend ()->Unit){
        viewModelScope.launch {
            try {
                progress.postValue(true)
                func()
                progress.postValue(false)
            }catch (e: Exception){
                progress.postValue(false)
                error.postValue(e)
                e.printStackTrace()
            }
        }
    }

    fun executeNoProgress(func: suspend ()->Unit){
        viewModelScope.launch {
            try {
                func()
            }catch (e: Exception){
                error.postValue(e)
                e.printStackTrace()
            }
        }
    }
}