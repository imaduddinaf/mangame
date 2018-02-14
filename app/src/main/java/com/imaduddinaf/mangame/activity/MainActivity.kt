package com.imaduddinaf.mangame.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.imaduddinaf.mangame.Logger
import com.imaduddinaf.mangame.R
import com.imaduddinaf.mangame.network.APIManager
import com.imaduddinaf.mangame.network.service.MangaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        request()

        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
