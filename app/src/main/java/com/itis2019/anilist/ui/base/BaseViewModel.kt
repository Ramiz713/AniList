package com.itis2019.anilist.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val loadingData = MutableLiveData<Boolean>()
    protected var errorData = MutableLiveData<Throwable>()

    fun isLoading(): LiveData<Boolean> = loadingData

    fun error(): LiveData<Throwable> = errorData

    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> invokeSuspend(suspendBlock: suspend () -> T): T? = try {
        loadingData.postValue(true)
        suspendBlock.invoke()
    } catch (throwable: Throwable) {
        errorData.postValue(throwable)
        null
    } finally {
        loadingData.postValue(false)
    }
}
