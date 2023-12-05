package com.example.mainproject.svplanViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainproject.repository.SvplanRepository

class SvPlanViewModelFactory(private val svplanRepository: SvplanRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SvPlanViewModel::class.java)) {
            return SvPlanViewModel(svplanRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}