package com.imaduddinaf.mangame.core

import android.app.Fragment
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment

/**
 * Created by Imaduddin Al Fikri on 28-Feb-18.
 */

typealias VoidClosure = () -> Void

@EFragment
open class BaseFragment : Fragment() {

    private var isAfterViewOrInjection: Boolean = false
    private var queue: ArrayList<VoidClosure> = ArrayList()

    @AfterViews
    open fun afterViews() {
        isAfterViewOrInjection = true

        this.activityHolder().title = customTitle()

        for (task in queue) {
            task()
        }

        queue.clear()
    }

    @AfterInject
    open fun afterInject() {
        isAfterViewOrInjection = true
    }

    override fun onDetach() {
        super.onDetach()

        isAfterViewOrInjection = false
    }

    override fun onDestroy() {
        super.onDestroy()

        isAfterViewOrInjection = false
    }

    open fun isAfterViewOrInjection(): Boolean {
        return isAfterViewOrInjection
    }

    fun addIntoQueue(task: VoidClosure) {
        queue.add(task)
    }

    protected fun activityHolder(): BaseActivity {
        return this.activity as BaseActivity
    }

    open fun customTitle(): String {
        return activityHolder().customTitle()
    }
}