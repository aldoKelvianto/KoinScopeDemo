package com.linecorp.devdaysched.speaker.data

import com.linecorp.devdaysched.speaker.SpeakerServices
import io.reactivex.Single

class CloudSpeakerRepository(
    private val speakerServices: SpeakerServices
) : SpeakerRepository {

    override fun fetchSpeakerList(): Single<List<SpeakerModel>> =
        speakerServices.speakerList()
}
