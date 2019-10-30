package com.linecorp.devdaysched.news

interface NewsContract {

    interface View {
        fun showNews(news: String)
        fun showError()
    }

    interface Presenter {
        fun loadNews()
        fun subscribe(view: View)
        fun unsubscribe()
    }
}
