package com.omerilhanli.appcentchallenge.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Her RecyclerView componenti için gerekli binding ve mDataList ikilisi, inheritance esnasında
 * oluşturulur. RecyclerView için gerekli adapter'e base yapı sağlar.
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder, D>(var mDataList: ArrayList<D>? = null) :
    RecyclerView.Adapter<VH>() {

    lateinit var binding: ViewDataBinding

    /**
     * Overrirde edilerek, binding nesnesi onCreateView esnasında initialize edilir.
     */
    @LayoutRes
    open var itemLayoutId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemLayoutId, parent, false)
        return createdView(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindView(holder, position)
    }

    override fun getItemCount() = mDataList?.size ?: 0

    /**
     * Liste temizlenir
     */
    fun clear() {
        mDataList?.clear()
        notifyDataSetChanged()
    }

    /**
     * Listeye yeni elemanlar eklenir
     */
    fun add(newList: ArrayList<D>?) {
        newList?.let {
            mDataList?.addAll(it)
            notifyDataSetChanged()
        }
    }

    abstract fun createdView(parent: ViewGroup, viewType: Int): VH
    abstract fun bindView(holder: VH, position: Int)

    interface ItemClickListener<Type> {
        fun onClickItem(data: Type)
    }
}