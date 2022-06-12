package com.djt.githubrepos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class TrendingViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = TrendingRepo(application)

    fun getItemDetails() {
        mRepo.getItem()
    }
    fun observerSuccessList(): MutableLiveData<ArrayList<trendinglist>> {
        return mRepo.observerSuccessList()
    }
    fun observeErrorItem(): MutableLiveData<String> {
        return mRepo.observeErrorItem()
    }

    fun observerSuccessItem(): MutableLiveData<String> {
        return mRepo.observerSuccessItem()
    }
}