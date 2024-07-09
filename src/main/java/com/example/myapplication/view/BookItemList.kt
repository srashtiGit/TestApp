package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.BookListItem

@Composable
fun BookItemList(bookList:List<BookListItem>, book:(BookListItem)->Unit){
    LazyColumn{
        items(items = bookList, itemContent = {
            ListItem(data = it, navigateToDetail = book)
        })
    }
}

@Composable
fun ListItem(data: BookListItem, navigateToDetail: (BookListItem)->Unit){
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),border = BorderStroke(2.dp,
            Color.Green),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navigateToDetail(data) }){
        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
            Text(
                text = data.title,
                fontSize = 14.sp, textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
        ListRow(label = stringResource(R.string.price), labelValue = data.price.toString()+" "+data.currencyCode)
        ListRow(label = stringResource(R.string.author), labelValue = data.author)
    }
}

@Composable
fun ListRow(label:String,labelValue:String){
    Spacer(modifier = Modifier.width(8.dp))
    Row {
        Text(
            text = label, fontSize = 14.sp, textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = labelValue, fontSize = 14.sp, textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ErrorView(message:String,retry: () -> Unit = { }){
    Column {

        Text(
            text = message, modifier = Modifier
                .padding(30.dp)
                .fillMaxSize()
                .size(20.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedButton(onClick = { retry()}) {
            Text("Retry")
        }
    }
}