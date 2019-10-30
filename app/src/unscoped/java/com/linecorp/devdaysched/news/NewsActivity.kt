package com.linecorp.devdaysched.news

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.linecorp.devdaysched.R
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.android.ext.android.inject

/**
 * NewsActivity uses MVP pattern and RxJava
 */
class NewsActivity : AppCompatActivity(), NewsContract.View {

    private val presenter: NewsContract.Presenter by inject()

    // To simulate multiple objects creation/injection
    private val largeObjects: LargeObjects by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        presenter.subscribe(this)
        presenter.loadNews()
        checkLargeObjects()
    }

    private fun checkLargeObjects() {
        val text = "LargeObjects hashCode ${largeObjects.hashCode()}"
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showError() {
        updateTextViewNews("error")
    }

    override fun showNews(news: String) {
        updateTextViewNews(news)
    }

    private fun updateTextViewNews(text: String) {
        textViewNews.text = text
    }
}
