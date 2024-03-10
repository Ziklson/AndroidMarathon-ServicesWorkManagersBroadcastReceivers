package com.example.twoscreenapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.twoscreenapp.model.CatFact
import com.example.twoscreenapp.service.CatFactService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _catFacts = MutableLiveData<List<CatFact>>()
    val catFacts: LiveData<List<CatFact>> = _catFacts

    fun startService(context: Context) {
        viewModelScope.launch {
            val intent = Intent(context, CatFactService::class.java)
            context.startService(intent)
        }
    }

    fun startWorkManager(context: Context, request: OneTimeWorkRequest) {
        viewModelScope.launch {
            WorkManager.getInstance(context).enqueueUniqueWork("myWork",ExistingWorkPolicy.REPLACE,request)
        }
    }

    fun updateCatFacts(catFacts: List<CatFact>) {
        _catFacts.value = catFacts
    }
}