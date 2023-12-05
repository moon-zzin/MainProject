package com.example.mainproject.svplanViewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainproject.repository.SvplanRepository


//parameter가 있는 ViewModel -> viewModel Factory 사용
class SvPlanViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SvPlanViewModel::class.java)) {
            return SvPlanViewModel(SvplanRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
