package com.omerilhanli.appcentchallenge.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.asistive.tool.AutoFitGridLayoutManager
import com.omerilhanli.appcentchallenge.databinding.FragmentRecentPhotosBinding
import com.omerilhanli.appcentchallenge.ui.adapter.RecyclerPhotosAdapter
import com.omerilhanli.appcentchallenge.ui.base.BaseAdapter
import com.omerilhanli.appcentchallenge.ui.base.BaseFragment
import com.omerilhanli.appcentchallenge.ui.main.MainActivity
import com.omerilhanli.appcentchallenge.ui.main.MainViewModel

class RecentPhotosFragment : BaseFragment<FragmentRecentPhotosBinding, MainViewModel>(),
    SwipeRefreshLayout.OnRefreshListener,
    BaseAdapter.ItemClickListener<Photo>,
    RecyclerPhotosAdapter.OnScrollToBottom {

    override var layoutId: Int = R.layout.fragment_recent_photos

    lateinit var adapter: RecyclerPhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerPhotosAdapter.instance
        adapter.onScrollToBottom = this
        adapter.itemClickListener = this

        with(binding) {
            lifecycleOwner = this@RecentPhotosFragment
            viewModel = this@RecentPhotosFragment.viewModel
            handler = this@RecentPhotosFragment.viewModel

            recyclerRecentlyPhotos.adapter = adapter
            recyclerRecentlyPhotos.layoutManager = AutoFitGridLayoutManager(context)
            swipeRefreshLayoutRecentPhotos.setOnRefreshListener(this@RecentPhotosFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigator = activity as MainActivity

        viewModel.recentPhotos.observe(viewLifecycleOwner, Observer {
            adapter.add(it?.photos?.photoArrayList)
        })
    }

    override fun onClickItem(data: Photo) {
        viewModel.navigator?.navigateToDetailActivity(data)
    }

    override fun onRefresh() {
        adapter.clear()
        viewModel.navigator?.fetchRecentPhotos(isPageIncrease = false)
    }

    override fun onScrollToBottom(visible: Boolean) {
        if (visible) {
            viewModel.navigator?.fetchRecentPhotos(isPageIncrease = true)
        }
    }

    companion object {
        fun newInstance(): RecentPhotosFragment {
            return RecentPhotosFragment()
        }
    }
}