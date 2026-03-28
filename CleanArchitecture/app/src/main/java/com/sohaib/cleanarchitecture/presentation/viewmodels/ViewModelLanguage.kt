package com.sohaib.cleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sohaib.cleanarchitecture.domain.entities.Language
import com.sohaib.cleanarchitecture.domain.usecases.UseCaseLanguage

class ViewModelLanguage(private val useCaseLanguage: UseCaseLanguage) : ViewModel() {

    private val _languagesLiveData = MutableLiveData<List<Language>>()
    val languageLiveData: LiveData<List<Language>> get() = _languagesLiveData

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        _languagesLiveData.value = useCaseLanguage.fetchLanguages()
    }

    fun updateLanguage(selectedCode: String) {
        _languagesLiveData.value = useCaseLanguage.updateLanguages(selectedCode)
    }
}