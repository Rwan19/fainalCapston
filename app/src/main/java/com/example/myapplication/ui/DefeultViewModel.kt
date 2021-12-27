package com.example.myapplication.ui

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Event
import com.example.myapplication.Result

abstract class DefeultViewModel:ViewModel() {
    protected val mSnackBarText=MutableLiveData<Event<String>>()
    val snackBarText:LiveData<Event<String>> = mSnackBarText

    protected val mDataLoading = MutableLiveData<Event<Boolean>>()
    val dataLoading:LiveData<Event<Boolean>> = mDataLoading

    protected fun <T> onResult(mutableLiveData: MutableLiveData<T>?= null, result: Result<T>){

        when(result){
            is Result.Loading -> mDataLoading.value = Event(true)

            is Result.Error -> {
                mDataLoading.value = Event(false)
                result.msg?.let { mSnackBarText.value =Event(it) }
            }
            is Result.Success -> {
                mDataLoading.value - Result.Error
            }
        }
    }

}