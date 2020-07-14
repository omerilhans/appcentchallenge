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

/**
 * MainActivity'ye attach edilerek en son yayınlanan tüm resimlerin listelendiği parçadır.
 * Swipe, ItemClick ve ScrollBottom eventleri dinlenir.
 */
class RecentPhotosFragment : BaseFragment<FragmentRecentPhotosBinding, MainViewModel>(),
    SwipeRefreshLayout.OnRefreshListener,
    BaseAdapter.ItemClickListener<Photo>,
    RecyclerPhotosAdapter.OnScrollToBottom {

    // BaseFragment' a gönderilerek onCreateView ile binding instance'ı oluşturulur.
    override var layoutId: Int = R.layout.fragment_recent_photos

    lateinit var adapter: RecyclerPhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerPhotosAdapter.instance
        adapter.onScrollToBottom = this
        adapter.itemClickListener = this

        // BaseFragment'te üretilen binding için datalar ve configuration'lar set edilir.
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
        // Navigator olarak fragment'in bağlı olduğu MainActivity sağlanır.
        viewModel.navigator = activity as MainActivity

        // Apiden gelen response'a observe olunur.
        viewModel.recentPhotosLiveData.observe(viewLifecycleOwner, Observer {
            // Gelen response datası adapter'a notify edilir.
            adapter.add(it?.photos?.photoArrayList)
        })
    }

    /**
     * Listede tıklanan her item için click eventi sağlanır.
     * Photo pojo datası DetailActivity'ye gönderilir.
     */
    override fun onClickItem(data: Photo) {
        viewModel.navigator?.navigateToDetailActivity(data)
    }

    /**
     * Aşağı doğru swipe ile refresh işlemi sağlanır.
     * Listedeki elemanlar silinir ve yeniden apiden data çekilir.
     */
    override fun onRefresh() {
        adapter.clear()
        viewModel.navigator?.fetchRecentPhotos(isPageIncrease = false)
    }

    /**
     * Listenin altına gelindiğinde yeni sayfa için apiye istek atılır.
     */
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