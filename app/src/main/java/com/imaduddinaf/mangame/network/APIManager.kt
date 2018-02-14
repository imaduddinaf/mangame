package com.imaduddinaf.mangame.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
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
                val okHTTPClient = OkHttpClient().newBuilder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()

                retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .client(okHTTPClient)
                        .baseUrl(baseURL)
                        .build()
            }

            return retrofit!!
        }

    inline fun <reified T> getService(): T {
        return service.create(T::class.java)
    }
}

// simplified chain-call
abstract class Observable2<T>: Observable<T>() {

    final fun call(onNext: (t: T) -> Unit, onError: (t: Throwable) -> Unit): Disposable? {
        return subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError)
    }
}
