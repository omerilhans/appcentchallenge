package com.omerilhanli.appcentchallenge.ui.base

import android.os.Bundle
import androidx.annotation.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.omerilhanli.appcentchallenge.R
import com.omerilhanli.appcentchallenge.asistive.alert.AppAlert
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, T : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseNavigator {

    @Inject
    lateinit var viewModel: T

    lateinit var binding: B

    @LayoutRes
    open var layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    fun replaceFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment, @Nullable tag: String) {
        supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .commitAllowingStateLoss()
    }

    override fun handleError(error: Throwable) {
        showErrorMessage(getMessageFromError(error))
    }

    private fun showErrorMessage(message: String) {
        AppAlert.showAlert(this, getString(R.string.popup_btn_title), message, getString(R.string.popup_btn_text))
    }

    private fun getMessageFromError(error: Throwable) = when (error) {
        is HttpException -> {
            when {
                error.response()?.code() == 404 -> {
                    resources.getString(R.string.err_not_found_404)
                }
                error.response()?.code() == 503 -> {
                    resources.getString(R.string.err_service_unavailable_503)
                }
                else -> {
                    resources.getString(R.string.err_generic_message)
                }
            }
        }
        is UnknownHostException -> {
            resources.getString(R.string.err_network_unavailable)
        }
        else -> {
            resources.getString(R.string.err_generic_message)
        }
    }


}