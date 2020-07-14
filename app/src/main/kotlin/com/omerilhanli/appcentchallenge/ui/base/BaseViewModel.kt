package com.omerilhanli.appcentchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omerilhanli.api_mdl.data.scheduler.AppScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Her spesifik ViewModel için navigator, isLoading property'leri ve completion davranışını sağlayan base class'tır.
 */
abstract class BaseViewModel<N>(private val scheduler: AppScheduler) : ViewModel() {

    /**
     * Api call ile başlama ve bitiş durumları boolean olarak tutulur.
     * Bu değerle loading işlemi gerçekleştirilir.
     */
    open val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * Reactive olarak çağırılan istekler ile dönen Disposable nesneleri composite olarak tutulur.
     */
    private val compositeDisposable = CompositeDisposable()
    /**
     * Navigator interface'leri sızıntıları önlemek için WeakReference ile tutulur
     */
    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() = mNavigator?.get()
        set(navigator) {
            this.mNavigator = if (navigator != null) WeakReference(navigator) else null
        }

    /**
     * App kill olduğunda dispose() ile tüm kaynaklar release edilir.
     */
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    /**
     * Her api isteğinde gerekli observeOn, subscribeOn ile onSubscribe, onComplete ve onError action'ları
     * implement edilir.
     */
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
                        setIsLoading(false) // loading animasyonu gizlenir.
                    }, {
                        setIsLoading(true) // loading animasyonu gösterilir.
                    })

        compositeDisposable.add(disposable)
    }

    private fun setIsLoading(isLoading: Boolean) {
        this.isLoadingLiveData.value = isLoading
    }
}