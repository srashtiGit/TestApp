package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.example.myapplication.model.BookListItem
import com.example.myapplication.repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailViewModel(private val bookId:String, private val repository: APIRepository):ViewModel() {
    var book = MutableLiveData<BookListItem>()
    var errorMsg =MutableLiveData<String>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBookListItemDetail(bookId,object :APIRepository.Callback<BookListItem>{
                override fun onSuccess(response: BookListItem) {
                    Log.d("srashti success",response.title)
                    book.value=response

                }

                override fun onError(error: VolleyError, errorInfo: BookListItem?) {
                    errorMsg.value=error.message
                    Log.d("srashti error",error.message.toString())                }

            })
        }
    }
}