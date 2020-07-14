package com.omerilhanli.appcentchallenge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.omerilhanli.appcentchallenge.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Her Fragment parçası için gerekli viewModel ve binding ikilisinin, generic olarak verilen tipler
 * ve layoutId aracılığıyla oluşturulması sağlanan base class'dır.
 */
abstract class BaseFragment<ChildBinding : ViewDataBinding, ChildViewModel : BaseViewModel<*>> :
    DaggerFragment() {

    lateinit var binding: ChildBinding

    @Inject
    lateinit var viewModel: ChildViewModel

    @LayoutRes
    open var layoutId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }
}