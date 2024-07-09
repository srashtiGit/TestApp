package com.example.myapplication.common

class Utilities {
    companion object{
        var baseUrl:String=AppConstants.PROD
        fun setServer(url:String){
            baseUrl=url;
        }
    }
}