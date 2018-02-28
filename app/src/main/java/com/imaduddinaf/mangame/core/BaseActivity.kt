package com.imaduddinaf.mangame.core

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.imaduddinaf.mangame.R
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

/**
 * Created by Imaduddin Al Fikri on 28-Feb-18.
 */

@EActivity(R.layout.activity_blank)
open class BaseActivity : AppCompatActivity() {

    private val defaultTitle: String = "Mangame"
    private var isAfterViewOrInjection: Boolean = false

    @AfterViews
    open fun afterViews() {
        isAfterViewOrInjection = true

        title = customTitle()

        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowBackButton())
        supportActionBar?.setHomeButtonEnabled(shouldShowBackButton())
    }

    @AfterInject
    open fun afterInject() {
        isAfterViewOrInjection = true
    }

    override fun onPause() {
        super.onPause()

        isAfterViewOrInjection = false
    }

    override fun onStop() {
        super.onStop()

        isAfterViewOrInjection = false
    }

    override fun onDestroy() {
        super.onDestroy()

        isAfterViewOrInjection = false
    }

    open fun customTitle(): String {
        return defaultTitle
    }

    fun isAfterViewOrInjection(): Boolean {
        return isAfterViewOrInjection
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && (item.itemId == android.R.id.home && shouldShowBackButton())) finish()

        return super.onOptionsItemSelected(item)
    }

    open fun shouldShowBackButton(): Boolean {
        return true
    }
}