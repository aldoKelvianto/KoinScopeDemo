package com.linecorp.devdaysched.news.data

import io.reactivex.Observable

class LocalNewsRepository : NewsRepository {
    override fun getAnnouncement(): Observable<NewsModel> =
        Observable.just("Lunch in 30 minutes")
}
