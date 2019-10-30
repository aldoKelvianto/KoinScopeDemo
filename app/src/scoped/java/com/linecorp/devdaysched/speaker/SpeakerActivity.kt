package com.linecorp.devdaysched.speaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linecorp.devdaysched.R
import com.linecorp.devdaysched.speaker.data.SpeakerModel
import com.linecorp.devdaysched.speaker.data.SpeakerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_speaker.*
import org.koin.androidx.scope.currentScope

/**
 * SpeakerActivity don't use any pattern, just RxJava
 */
class SpeakerActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private val cloudSpeakerRepository: SpeakerRepository by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker)

        val disposable = cloudSpeakerRepository.fetchSpeakerList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showSpeakerList(it)
            }, {
                showError()
            })
        disposables.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun showError() {
        updateTextViewSpeakers("Error")
    }

    private fun showSpeakerList(speakerList: List<SpeakerModel>) {
        val speakers = speakerList.map { it.name }
            .fold(StringBuilder()) { builder, name ->
                builder.apply {
                    append(name)
                    append("\n")
                }
            }.toString()
        updateTextViewSpeakers(speakers)
    }

    private fun updateTextViewSpeakers(text: String) {
        textViewSpeakers.text = text
    }
}
