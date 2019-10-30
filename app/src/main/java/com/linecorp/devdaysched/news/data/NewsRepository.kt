package com.linecorp.devdaysched.news.data

import io.reactivex.Observable

interface NewsRepository {

    fun getAnnouncement(): Observable<NewsModel>
}
