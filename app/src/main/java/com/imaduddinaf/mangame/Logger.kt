package com.imaduddinaf.mangame

import android.util.Log

/**
 * Created by Imaduddin Al Fikri on 14-Feb-18.
 */

class Logger {

    companion object {

        fun show(message: String) {
            Log.d("DEBUG", message)
        }

        fun showSuccess(message: String) {
            Log.d("SUCCESS", message)
        }

        fun showError(message: String) {
            Log.e("ERROR", message)
        }
    }
}