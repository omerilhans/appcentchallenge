package com.omerilhanli.appcentchallenge.ui.detail

import android.os.Bundle
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.extensive.KEY_INTENT_PHOTO
import com.omerilhanli.appcentchallenge.asistive.util.PhotoPathUtil
import com.omerilhanli.appcentchallenge.databinding.ActivityDetailBinding
import com.omerilhanli.appcentchallenge.ui.base.BaseActivity
import com.omerilhanli.appcentchallenge.ui.detail.fragment.InfoPhotoFragment

/**
 * Çekilen resim tam ekran halinde gösterilir.
 */
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailNavigator {

    // layoutId BaseActivity'ye paslanır
    override var layoutId: Int = R.layout.activity_detail

    lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // BaseActivity'de oluşturulan binding nesnesine gerekli binding dataları paslanır.
        with(binding) {
            lifecycleOwner = this@DetailActivity
            handler = this@DetailActivity.viewModel
        }

        // DetailViewModel için navigator olarak DetailActivit set edilir.
        viewModel.navigator = this

        // DetailActivit'den intent ile paslanan photo objesi alınır.
        intent.apply {
            photo = getParcelableExtra(KEY_INTENT_PHOTO) as Photo
        }

        // viewModel içindeki photoInfoMap objesi map edilir.
        viewModel.photoInfoMap.title = photo.title
        viewModel.photoInfoMap.desc = photo.description
        viewModel.photoInfoMap.photoPath = PhotoPathUtil.getBigPhotoPath(photo)
    }

    /**
     * Navigator aracılığıyla ilgili photo için PhotoInfo detayı apiden alınır.
     */
    override fun fetchPhotoInfo() {
        viewModel.getPhotoInfo(photo.id)
    }

    /**
     * Navigator aracılığıyla InfoPhotoFragment Activity'ye attach edilir.
     */
    override fun showInfoPhotoFragment() {
        replaceFragment(R.id.frame_container, InfoPhotoFragment.newInstance(), InfoPhotoFragment::class.java.simpleName)
    }

    /**
     * Navigator aracılığıyla Back event action'u sağlar.
     */
    override fun navigateBack() {
        onBackPressed()
    }
}
