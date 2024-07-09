package com.example.myapplication.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.APIRepository
import com.example.myapplication.viewmodel.BookDetailViewModel
import com.example.myapplication.viewmodel.BookListViewModel

class MainViewModelFactory @JvmOverloads constructor(private val repository:APIRepository,private val bookID:String=""):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java))
        return BookListViewModel(repository) as T
        else if(modelClass.isAssignableFrom(BookDetailViewModel::class.java))
            return BookDetailViewModel(bookID,repository) as T
        throw IllegalArgumentException("unknown viewmodel class")
    }

}