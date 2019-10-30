package com.linecorp.devdaysched

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linecorp.devdaysched.news.NewsActivity
import com.linecorp.devdaysched.speaker.SpeakerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initClickListeners()
    }

    private fun initClickListeners() {
        btnNews.setOnClickListener {
            startNewsActivity()
        }
        btnSpeaker.setOnClickListener {
            startSpeakerActivity()
        }
    }

    private fun startNewsActivity() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }

    private fun startSpeakerActivity() {
        val intent = Intent(this, SpeakerActivity::class.java)
        startActivity(intent)
    }
}
