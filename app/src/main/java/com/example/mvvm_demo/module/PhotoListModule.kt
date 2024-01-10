package com.example.mvvm_demo.module

import com.example.mvvm_demo.viewmodel.PhotoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoListModule = module {
    viewModel {
        PhotoListViewModel(get(), get())
    }
}