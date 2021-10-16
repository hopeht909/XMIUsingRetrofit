package com.example.xmlusingretrofit

import retrofit2.http.GET
import com.example.xmlusingretrofit.model.Feed
import retrofit2.Call

interface FeedAPI {
    @get:GET("cats/.rss")
    val feed: Call<Feed?>?

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }
}