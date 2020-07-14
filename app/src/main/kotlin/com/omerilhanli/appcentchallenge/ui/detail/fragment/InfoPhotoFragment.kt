package com.omerilhanli.appcentchallenge.ui.detail.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.databinding.FragmentInfoBinding
import com.omerilhanli.appcentchallenge.ui.base.BaseFragment
import com.omerilhanli.appcentchallenge.ui.detail.DetailActivity
import com.omerilhanli.appcentchallenge.ui.detail.DetailViewModel

/**
 * Apiden alınan Photo ve PhotoInfo nesneleri PhotoInfoMap nesnesine map edilir ve
 * tam ekran olarak gösterilir.
 */
class InfoPhotoFragment : BaseFragment<FragmentInfoBinding, DetailViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    // BaseFragment' a gönderilerek onCreateView ile binding instance'ı oluşturulur.
    override var layoutId: Int = R.layout.fragment_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // BaseFragment'te üretilen binding için datalar ve configuration'lar set edilir.
        with(binding) {
            lifecycleOwner = this@InfoPhotoFragment
            viewModel = this@InfoPhotoFragment.viewModel
            handler = this@InfoPhotoFragment.viewModel

            swipeRefreshLayoutPhotoInfo.setOnRefreshListener(this@InfoPhotoFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Navigator olarak fragment'in bağlı olduğu DetailActivity sağlanır.
        viewModel.navigator = activity as DetailActivity

        // Apiden gelen response'a observe olunur.
        viewModel.photoInfoLiveData.observe(viewLifecycleOwner, Observer { response ->
            viewModel.photoInfoMap
                .apply {
                    username = response.photo?.owner?.username
                }.also {
                    binding.photoInfoMap = it
                }
        })
    }

    /**
     * Alınan resim aşağı tekrar swipe işlemi ile yenilenir.
     */
    override fun onRefresh() {
        binding.imgPhoto
            .apply {
                setImageResource(R.drawable.icn_default_image)
            }.also {
                viewModel.navigator?.fetchPhotoInfo()
            }
    }

    companion object {
        fun newInstance(): InfoPhotoFragment {
            return InfoPhotoFragment()
        }
    }
}