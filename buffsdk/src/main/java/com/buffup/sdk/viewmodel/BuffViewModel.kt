package com.buffup.sdk.viewmodel

import androidx.lifecycle.*
import com.buffup.sdk.model.Result
import com.buffup.sdk.repository.BuffRepository
import kotlinx.coroutines.launch

class BuffViewModel: ViewModel() {

    private val repository = BuffRepository()

    private var  _buff: MutableLiveData<Result> = MutableLiveData()
    var buff: LiveData<Result> = _buff

    fun loadBuff(buffId: Long) {
        viewModelScope.launch {
            _buff.value = repository.loadBuff(buffId)
        }
    }


}