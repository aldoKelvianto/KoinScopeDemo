package com.linecorp.devdaysched.news

import androidx.lifecycle.LifecycleObserver
import com.linecorp.devdaysched.news.data.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsPresenter(
    private val repository: NewsRepository
) : NewsContract.Presenter, LifecycleObserver {

    private var view: NewsContract.View? = null

    private val disposables = CompositeDisposable()

    override fun subscribe(view: NewsContract.View) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        disposables.clear()
    }

    override fun loadNews() {
        val disposable = repository.getAnnouncement()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showNews(it)
            }, {
                view?.showError()
            })
        disposables.add(disposable)
    }
}
