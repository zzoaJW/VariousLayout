package com.z0o0a.variouslayout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.z0o0a.variouslayout.model.Banner

class MainActivityVM : ViewModel() {
    var _cooVerti = MutableLiveData<Float>()
    private val _bannerList = MutableLiveData<ArrayList<Banner>>()
    private var items = ArrayList<Banner>()

    init {
        _cooVerti.value = 0f
        items = arrayListOf(Banner("NEW"), Banner("RABBIT"), Banner("YEAR!"), Banner("=(:3"))
        _bannerList.value = items
    }

    val bannerList: LiveData<ArrayList<Banner>> get() = _bannerList

    fun getAll():LiveData<ArrayList<Banner>>{
        return bannerList
    }
}