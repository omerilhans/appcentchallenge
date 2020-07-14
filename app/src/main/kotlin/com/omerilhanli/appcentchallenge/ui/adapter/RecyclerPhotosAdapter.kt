package com.omerilhanli.appcentchallenge.ui.adapter

import android.view.ViewGroup
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.ui.adapter.viewholder.PhotosViewHolder
import com.omerilhanli.appcentchallenge.ui.base.BaseAdapter

class RecyclerPhotosAdapter(list: ArrayList<Photo>?) : BaseAdapter<PhotosViewHolder, Photo>(list) {

    var itemClickListener: ItemClickListener<Photo>? = null

    var onScrollToBottom: OnScrollToBottom? = null

    override var itemLayoutId: Int = R.layout.item_photo

    override fun createdView(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(binding, itemClickListener)
    }

    override fun bindView(holder: PhotosViewHolder, position: Int) {
        mDataList?.let {
            holder.bind(it[position])
        }

        if (position == itemCount - 1) {
            onScrollToBottom?.onScrollToBottom(true)
        } else {
            onScrollToBottom?.onScrollToBottom(false)
        }
    }

    interface OnScrollToBottom {
        fun onScrollToBottom(visible: Boolean)
    }

    companion object {
        val instance = RecyclerPhotosAdapter(ArrayList())
    }
}

