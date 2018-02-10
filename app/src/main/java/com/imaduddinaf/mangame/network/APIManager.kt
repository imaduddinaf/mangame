package com.imaduddinaf.mangame.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Imaduddin Al Fikri on 10-Feb-18.
 */

object APIManager {

    val baseURL: String = "http://www.mangaeden.com/api/"

    private var retrofit: Retrofit? = null
    val service: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseURL)
                        .build()
            }

            return retrofit!!
        }
}