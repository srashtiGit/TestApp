package com.example.myapplication.repository

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyRequest {
     companion object{
           var queue: RequestQueue?=null
         fun getQueue(context: Context): RequestQueue{
             if(queue==null)
              queue= Volley.newRequestQueue(context)
             return  queue!!

         }
     }
}