package com.example.myapplication.repository

import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.myapplication.common.Utilities
import com.example.myapplication.model.BookList
import com.example.myapplication.model.BookListItem
import com.google.gson.Gson

class APIRepository(queue:RequestQueue) {
    private var queue:RequestQueue?=null
    interface Callback<T>{
        fun onSuccess(response:T)
        fun onError(error:VolleyError,errorInfo:T?)
    }
    companion object{
        private var repository:APIRepository?=null
        fun getInstance(queue: RequestQueue):APIRepository{
            synchronized(APIRepository) {
                if (repository == null)
                    repository = APIRepository(queue)
                return repository!!
            }
        }
    }
    init {
        this.queue=queue
    }
    fun getBookList(callback: Callback<BookList>) {
        //constructing url can also be put in a separate file for modularisation as app grows
       val url=Utilities.baseUrl+"books"
        val jsonRequest= StringRequest(url,{ response: String? ->
          try{
              val bookList=Gson().fromJson(response, BookList::class.java)
              Log.d("srashti json parsed",response.toString())
              callback.onSuccess(bookList)
          }catch (e:Exception){

              Log.d("srashti json parse error",response.toString())
          }
        }, {
            callback.onError(it,null)
        }
        )
        queue?.add(jsonRequest)
    }
    fun getBookListItemDetail(id:String,callback: Callback<BookListItem>) {
        val url=Utilities.baseUrl+"book/$id"
        val jsonRequest= StringRequest(url,{ response: String? ->
            try{
                val employee=Gson().fromJson(response, BookListItem::class.java)
                Log.d("srashti json parsed",response.toString())
                callback.onSuccess(employee)
            }catch (e:Exception){
                Log.d("srashti json parse error",response.toString())
            }
        }, {
            callback.onError(it,null)
        }
        )
        queue?.add(jsonRequest)
    }
}