package com.linecorp.devdaysched.di

import com.linecorp.devdaysched.news.LargeObjects
import com.linecorp.devdaysched.news.NewsContract
import com.linecorp.devdaysched.news.NewsPresenter
import com.linecorp.devdaysched.news.data.LocalNewsRepository
import com.linecorp.devdaysched.news.data.NewsRepository
import com.linecorp.devdaysched.speaker.SpeakerServices
import com.linecorp.devdaysched.speaker.data.CloudSpeakerRepository
import com.linecorp.devdaysched.speaker.data.SpeakerRepository
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal

val newsModule = module {
    single<Long> { 999_999_999_999L }
    single<String> { getLongString() }
    single<BigDecimal> { BigDecimal(999_999_999_999L) }
    single<LargeObjects> { LargeObjects(get(), get(), get()) }
    single<NewsRepository> { LocalNewsRepository() }
    single<NewsContract.Presenter> {
        NewsPresenter(get())
    }
}

private fun getLongString() =
    """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi luctus interdum ipsum, eget semper leo rutrum eu. Sed eu sagittis mi. Quisque vitae euismod ante. Pellentesque vitae hendrerit erat. Sed commodo egestas orci, sit amet congue quam faucibus ac. Fusce vitae est vel sapien faucibus consectetur quis ac est. Aenean et consequat diam. Aenean aliquam dapibus erat in ullamcorper. Ut id lectus pellentesque, viverra leo eu, facilisis quam. Aliquam cursus fringilla hendrerit. Integer tempor cursus dignissim.

Phasellus eget auctor erat, non maximus ipsum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget condimentum tortor. Duis elit nisl, luctus at convallis a, vestibulum at erat. Donec nisl lectus, pellentesque at justo in, hendrerit viverra elit. Sed ut orci lorem. Mauris quis justo porttitor, eleifend purus volutpat, molestie orci. Morbi porta rhoncus lorem sit amet eleifend. Aliquam pellentesque sodales ante. Ut vel tortor at felis euismod cursus vitae auctor lorem.

Sed laoreet in turpis luctus scelerisque. Duis pharetra nibh vel pulvinar bibendum. Maecenas justo lacus, ultricies eget sapien sit amet, maximus viverra lacus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Suspendisse sagittis lacus ac ex maximus, vitae tincidunt est tristique. Morbi consequat mollis urna, nec molestie turpis faucibus at. Praesent in posuere felis. Morbi interdum turpis sit amet placerat consequat. Nullam metus ligula, accumsan quis leo sit amet, congue tincidunt dui. Vestibulum mollis vel eros vitae vulputate. Cras varius libero et fermentum convallis. Nunc bibendum ex vitae libero rutrum tincidunt. Cras nec bibendum urna, vel rhoncus nibh. Suspendisse condimentum eros quis placerat maximus. Duis a fringilla quam, ut ultrices velit. Integer elementum scelerisque neque, in imperdiet felis.

Vivamus sagittis viverra nisl, vitae vehicula diam. Pellentesque at leo tempor, ornare ante sit amet, rhoncus elit. Morbi convallis molestie urna eget ultricies. Ut vehicula dui ut maximus semper. Nam porttitor tincidunt hendrerit. Duis diam neque, molestie vestibulum nulla nec, hendrerit commodo eros. Sed gravida et nibh vitae aliquet.

Proin metus orci, molestie at turpis sit amet, vestibulum ultrices ante. Fusce et dictum ligula, lobortis molestie lorem. Morbi pharetra aliquam dolor ac posuere. Morbi dignissim lacus et odio tincidunt efficitur. Morbi fringilla lorem diam, in consequat dolor viverra eu. Nam lobortis porta suscipit. Aliquam erat volutpat. Cras vitae nibh a diam mattis ultricies aliquet id diam. Morbi non mollis lorem. Vestibulum sit amet tristique ipsum. Integer at pellentesque lorem, vitae euismod ipsum. Phasellus at imperdiet massa, quis mollis velit. Sed luctus blandit faucibus. Sed gravida, purus eu mollis semper, quam nulla rhoncus dolor, et imperdiet arcu turpis vitae erat. Maecenas at nunc libero. Morbi non tellus at eros semper auctor nec non turpis.        
    """

val speakerModule = module {
    single<ChuckInterceptor> { ChuckInterceptor(get()) }
    single<OkHttpClient> { createOkHttpClient(get()) }
    single<Converter.Factory> { GsonConverterFactory.create() }
    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single<Retrofit> {
        createRetrofit(
            get(),
            getProperty(PropertiesKey.SERVER_URL.key),
            get(),
            get()
        )
    }
    single<SpeakerServices> { createSpeakerServices(get()) }
    single<SpeakerRepository> { createSpeakerRepository(get()) }
}

private fun createRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
    converterFactory: Converter.Factory,
    callAdapterFactory: CallAdapter.Factory
): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

private fun createOkHttpClient(chuckInterceptor: ChuckInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(chuckInterceptor)
        .build()

private fun createSpeakerRepository(speakerServices: SpeakerServices) =
    CloudSpeakerRepository(speakerServices)

private fun createSpeakerServices(retrofit: Retrofit) =
    retrofit.create(SpeakerServices::class.java)
