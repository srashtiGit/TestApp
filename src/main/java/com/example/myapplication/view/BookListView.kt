package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.common.AppConstants
import com.example.myapplication.repository.APIRepository
import com.example.myapplication.repository.VolleyRequest
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.BookListViewModel
import com.example.myapplication.common.MainViewModelFactory
import com.example.myapplication.common.Utilities

class BookListView : ComponentActivity() {
       private lateinit var viewModel: BookListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val queue=VolleyRequest.getQueue(this)
        val repository=APIRepository.getInstance(queue)
        viewModel= ViewModelProvider(this, MainViewModelFactory(repository))[BookListViewModel::class.java]
        initServer()
        observeData()
}

    private fun initServer() {
        //here we can modify it by build variant and change the url accordingly
        Utilities.setServer(AppConstants.PROD)
    }

    private fun observeData() {

        viewModel.bookList.observe(this, Observer {
            Log.d("srashti observe",it.size.toString())
            setContent {
                MyApplicationTheme{
                    Surface(color= MaterialTheme.colorScheme.background){
                        BookItemList(it){
                            //can use navController and fragments here but haven't worked with it in past so implemented like this for now
                            val intent=Intent(this,BookDetails::class.java).apply { putExtra(AppConstants.BOOK_ID,it.id.toString()) }
                            startActivity(intent)
                            Toast.makeText(this,"Book ${it.title}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
        viewModel.errorMsg.observe(this,Observer{
            setContent {
                MyApplicationTheme{
                    Surface(color= MaterialTheme.colorScheme.background) {
                        val msg=it?:"Sorry ! we couldn't fetch the data at the moment Please try again later"
                        ErrorView(message = msg){
                            viewModel.callListApi()
                        }
                    }}
            }
        })
    }

}


