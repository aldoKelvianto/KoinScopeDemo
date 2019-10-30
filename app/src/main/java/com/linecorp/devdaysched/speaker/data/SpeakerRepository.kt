package com.linecorp.devdaysched.speaker.data

import io.reactivex.Single

interface SpeakerRepository {

    fun fetchSpeakerList(): Single<List<SpeakerModel>>
}
