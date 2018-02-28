package com.imaduddinaf.mangame.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imaduddinaf.mangame.R
import com.imaduddinaf.mangame.core.BaseFragment
import org.androidannotations.annotations.EFragment

/**
 * Created by Imaduddin Al Fikri on 28-Feb-18.
 */

@EFragment(R.layout.fragment_home)
class HomeFragment : BaseFragment() {

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
