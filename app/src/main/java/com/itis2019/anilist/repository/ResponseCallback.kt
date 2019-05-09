package com.itis2019.anilist.repository

import android.arch.lifecycle.MutableLiveData

interface ResponseCallback {
    val isLoading: MutableLiveData<Boolean>
    val isError: MutableLiveData<Throwable>
}
