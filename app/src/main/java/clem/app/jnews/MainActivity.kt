package clem.app.jnews

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import clem.app.jnews.base.BaseActivity
import clem.app.jnews.bean.NewsItem
import clem.app.jnews.retrofit.RetrofitHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class MainActivity : BaseActivity() {
    private var newsAsync: Deferred<List<NewsItem>>? = null

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun cancelRequest() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        navigationView.run { setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener) }

        fab.run { setOnClickListener(onFabClickListener) }

    }

    private fun getNews() {
        var newsList: List<NewsItem>
        async(UI) {
            tryCatch({
                it.printStackTrace()
            }, {
                newsAsync?.cancelByActive()
                newsAsync = RetrofitHelper.retrofitService.getNewsList("national/list")
                val result = newsAsync?.await()
                result ?: let {
                    toast("Failed")
                    return@async
                }
                toast(result.size.toString())
            })
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_camera -> {
                        // Handle the camera action
                    }
                    R.id.nav_gallery -> {

                    }
                    R.id.nav_slideshow -> {

                    }
                    R.id.nav_manage -> {

                    }
                    R.id.nav_share -> {

                    }
                    R.id.nav_send -> {

                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }

    private val onFabClickListener =
            View.OnClickListener {
                Snackbar.make(coordinator, "Snack it", Snackbar.LENGTH_LONG).show()
            }

}
