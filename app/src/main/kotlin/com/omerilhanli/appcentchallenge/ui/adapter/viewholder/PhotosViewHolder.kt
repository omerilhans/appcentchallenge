package com.omerilhanli.appcentchallenge.ui.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_mdl.entity.Photo
import com.omerilhanli.appcentchallenge.BR
import com.omerilhanli.appcentchallenge.ui.base.BaseAdapter

class PhotosViewHolder(
    private val binding: ViewDataBinding,
    private val itemClickListener: BaseAdapter.ItemClickListener<Photo>? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        with(binding) {
            setVariable(BR.photo, photo)
            setVariable(BR.itemClickListener, itemClickListener)
            executePendingBindings()
        }
    }
}