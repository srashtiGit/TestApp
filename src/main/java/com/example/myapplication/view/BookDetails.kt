package com.example.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.common.AppConstants
import com.example.myapplication.repository.APIRepository
import com.example.myapplication.repository.VolleyRequest
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.BookDetailViewModel
import com.example.myapplication.common.MainViewModelFactory

class BookDetails: ComponentActivity() {
    private lateinit var viewModel: BookDetailViewModel
    private var bookId :String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val queue= VolleyRequest.getQueue(this)
        val repository= APIRepository.getInstance(queue)
        bookId=intent.extras?.getString(AppConstants.BOOK_ID)
        bookId?.let {  viewModel= ViewModelProvider(this, MainViewModelFactory(repository,it!!))[BookDetailViewModel::class.java]
            observeData()
        }?:run {
            //If bookId is empty then we can show some error UI
            setContent {
                MyApplicationTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        Text(text = stringResource(R.string.error_detail))
                    }
                }
            }
        }
    }

    private fun observeData() {
        viewModel.book.observe(this) {
            setContent {
                MyApplicationTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        BookDetailView(book = it){
                            Toast.makeText(this,"Back Pressed", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
            }
        }
    }
}