package com.omerilhanli.appcentchallenge.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.extensive.CHALLENGE_GITHUB_LINK
import com.omerilhanli.appcentchallenge.extensive.KEY_INTENT_PHOTO
import com.omerilhanli.appcentchallenge.extensive.openLinkOnBrowser
import com.omerilhanli.appcentchallenge.databinding.ActivityMainBinding
import com.omerilhanli.appcentchallenge.extensive.startThis
import com.omerilhanli.appcentchallenge.ui.base.BaseActivity
import com.omerilhanli.appcentchallenge.ui.detail.DetailActivity
import com.omerilhanli.appcentchallenge.ui.main.fragment.RecentPhotosFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override var layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@MainActivity
            handler = this@MainActivity.viewModel
        }

        viewModel.navigator = this

        viewModel.navigator?.fetchRecentPhotos(isPageIncrease = false)
    }

    override fun fetchRecentPhotos(isPageIncrease: Boolean) {
        if (isPageIncrease) {
            viewModel.increasePageNumber()
        } else {
            viewModel.resetPageNumber()
        }

        viewModel.getRecentPhotos()
    }

    override fun showRecentPhotoFragment() {
        replaceFragment(R.id.frame_container, RecentPhotosFragment.newInstance(), RecentPhotosFragment::class.java.simpleName)
    }

    override fun navigateToDetailActivity(photo: Photo) {
        startThis(DetailActivity::class.java, intentKey = KEY_INTENT_PHOTO, intentValue = photo)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_link -> openLinkOnBrowser(CHALLENGE_GITHUB_LINK)
        }
        return super.onOptionsItemSelected(item)
    }
}
