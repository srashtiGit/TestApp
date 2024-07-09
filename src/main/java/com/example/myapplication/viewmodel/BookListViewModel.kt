package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.example.myapplication.model.BookList
import com.example.myapplication.repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookListViewModel(val repository:APIRepository) :ViewModel() {

    var bookList =MutableLiveData<BookList>()
    var errorMsg =MutableLiveData<String>()

    init {
        callListApi()
    }
    fun callListApi(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBookList(object :APIRepository.Callback<BookList>{
                override fun onSuccess(response: BookList) {
                    Log.d("srashti success",response.get(0).title)
                    bookList.value=response

                }

                override fun onError(error: VolleyError, errorInfo: BookList?) {
                    errorMsg.value=error.message
                    Log.d("srashti error",error.message.toString())
                }
            })
        }
    }
}