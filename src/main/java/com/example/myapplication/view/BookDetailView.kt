package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.myapplication.model.BookListItem
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun BookDetailView(book: BookListItem, onNavIconPressed: () -> Unit = { }) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    DetailHeader(
                        scrollState,onNavIconPressed,
                        this@BoxWithConstraints.maxHeight
                    )
                    DetailContent(book, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun DetailHeader(
    scrollState: ScrollState,
    onNavIconPressed: () -> Unit,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }
    Image(
        modifier = Modifier
            .padding(15.dp).clickable { onNavIconPressed() },
        painter = painterResource(id =R.drawable.baseline_arrow_back_24 ),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id =R.drawable.ic_launcher_foreground ),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun DetailContent(book: BookListItem, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Name(book)
        BookProperty(stringResource(R.string.isbn), book.isbn)
        BookProperty(stringResource(R.string.price), book.price.toString()+" "+book.currencyCode)
        BookProperty(stringResource(R.string.author), book.author)
        BookProperty(stringResource(R.string.description), book.description)
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    book: BookListItem
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            book = book,
            modifier = Modifier.paddingFromBaseline(32.dp)
        )
    }
}

@Composable
private fun Name(book: BookListItem, modifier: Modifier = Modifier) {
    Text(
        text = book.title,
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BookProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
            Text(
                text = label,
                modifier = Modifier.paddingFromBaseline(24.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        val style = if (isLink) {
            MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyMedium
        }
        Text(
            text = value,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = style
        )
    }
}
@Preview
@Composable
fun ProfilePreview() {
    val book = BookListItem("srashti","eur",34,"gdcj",677,"title","fdjfdadjjfjdjahvcjhvdc")
    BookDetailView(book = book)
}

