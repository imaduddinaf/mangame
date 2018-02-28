package com.imaduddinaf.mangame.activity

import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.imaduddinaf.mangame.utils.Logger
import com.imaduddinaf.mangame.R
import com.imaduddinaf.mangame.core.BaseActivity
import com.imaduddinaf.mangame.core.BaseFragment
import com.imaduddinaf.mangame.fragment.HomeFragment
import com.imaduddinaf.mangame.network.APIManager
import com.imaduddinaf.mangame.network.service.MangaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@EActivity(R.layout.activity_main)
class MainActivity : BaseActivity() {

    private var disposable: Disposable? = null
    private var fragments: ArrayList<BaseFragment> = ArrayList()
    private var currentFragment: BaseFragment = HomeFragment()
    private var homeFragment: BaseFragment = HomeFragment()

    @ViewById(R.id.bottom_navigation)
    private val navigationView: BottomNavigationView? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        request()

        when (item.itemId) {
            R.id.navigation_home -> {
                if (currentFragment.javaClass == HomeFragment.javaClass) return@OnNavigationItemSelectedListener false

                currentFragment = homeFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }


        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.content, currentFragment)?.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentFragment = homeFragment

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.content, currentFragment)?.commit()
    }

    override fun afterViews() {
        navigationView?.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun request() {
        disposable = APIManager.getService<MangaService>()
                .getList(0, 25)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Logger.showSuccess("result total: " + result.end + ", " + result.mangas.size)

                            val manga = result.mangas[0]

                            Logger.show("manga: " + manga.id + ", " + manga.name + ", " + manga.image)
                        },
                        { error ->
                            Logger.showError("error: " + error.message)
                            Logger.showError("error loc: " + error.localizedMessage)
                        }
                )
    }
}
