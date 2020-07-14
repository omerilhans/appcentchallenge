package com.omerilhanli.appcentchallenge.ui.detail

import android.os.Bundle
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.extensive.KEY_INTENT_PHOTO
import com.omerilhanli.appcentchallenge.asistive.util.PhotoPathUtil
import com.omerilhanli.appcentchallenge.databinding.ActivityDetailBinding
import com.omerilhanli.appcentchallenge.ui.base.BaseActivity
import com.omerilhanli.appcentchallenge.ui.detail.fragment.InfoPhotoFragment

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailNavigator {

    override var layoutId: Int = R.layout.activity_detail

    lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@DetailActivity
            handler = this@DetailActivity.viewModel
        }

        viewModel.navigator = this

        intent.apply {
            photo = getParcelableExtra(KEY_INTENT_PHOTO) as Photo
        }

        viewModel.photoInfoMap.title = photo.title
        viewModel.photoInfoMap.desc = photo.description
        viewModel.photoInfoMap.photoPath = PhotoPathUtil.getBigPhotoPath(photo)
    }

    // call..
    override fun fetchPhotoInfo() {
        viewModel.getPhotoInfo(photo.id)
    }

    override fun showInfoPhotoFragment() {
        replaceFragment(R.id.frame_container, InfoPhotoFragment.newInstance(), InfoPhotoFragment::class.java.simpleName)
    }

    override fun navigateBack() {
        onBackPressed()
    }
}
