package com.imaduddinaf.mangame.network.service

import com.imaduddinaf.mangame.model.MangaListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Imaduddin Al Fikri on 10-Feb-18.
 */

 interface MangaService {

    @GET("list/0") // 0 for english language
    fun getList(@Query("p") page: Int,
                @Query("l") perPage: Int
    ): Observable<MangaListResponse>
}