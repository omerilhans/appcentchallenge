package com.omerilhanli.appcentchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(private val scheduler: AppScheduler) : ViewModel() {

    open val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val compositeDisposable = CompositeDisposable()
    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() = mNavigator?.get()
        set(navigator) {
            this.mNavigator = if (navigator != null) WeakReference(navigator) else null
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun <T> Observable<T>.completion(subscribing: (param: T) -> Unit) {
        val navigator = navigator as BaseNavigator
        val disposable =
            observeOn(scheduler.main())
                .subscribeOn(scheduler.io())
                .subscribe(
                    { response ->
                        subscribing(response)
                    }, { ex ->
                        setIsLoading(false)
                        navigator.handleError(ex)
                    }, {
                        setIsLoading(false)
                    }, {
                        setIsLoading(true)
                    })

        compositeDisposable.add(disposable)
    }

    private fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}