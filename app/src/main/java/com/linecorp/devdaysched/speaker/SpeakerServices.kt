package com.linecorp.devdaysched.speaker

import com.linecorp.devdaysched.speaker.data.SpeakerModel
import io.reactivex.Single
import retrofit2.http.GET

interface SpeakerServices {

    @GET("users")
    fun speakerList(): Single<List<SpeakerModel>>
}
