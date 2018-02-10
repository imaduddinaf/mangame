package com.imaduddinaf.mangame.core

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho

/**
 * Created by Imaduddin Al Fikri on 10-Feb-18.
 */

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Fresco.initialize(this)
    }
}