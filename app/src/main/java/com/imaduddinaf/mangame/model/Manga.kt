package com.imaduddinaf.mangame.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Imaduddin Al Fikri on 10-Feb-18.
 */

open class MangaListResponse {

    @SerializedName("end")
    @Expose
    var end: Int = 0

    @SerializedName("manga")
    @Expose
    var mangas: Array<Manga> = arrayOf<Manga>()
}

open class Manga {

    @SerializedName("i")
    @Expose
    var id: String = ""

    @SerializedName("t")
    @Expose
    var name: String = ""

    @SerializedName("c")
    @Expose
    var categories: Array<String> = arrayOf<String>()

    @SerializedName("im")
    @Expose
    var image: String = ""
}