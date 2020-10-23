package com.s10plus.core_application.base_ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.s10plus.core_application.network.ClientRetrofit
import com.s10plus.core_application.utils.SubscribeUtils
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer


open class BaseViewModel:ViewModel() {

    protected var loader        = MutableLiveData<Boolean>()
    protected var success       = MutableLiveData<Any>()
    protected var error         = MutableLiveData<String>()
    protected val disposable    = CompositeDisposable()
    protected val serverRetrofit = ClientRetrofit

    protected var isMock = false
    var liveDataManager: MediatorLiveData<BaseFethData> = fethData()


    open fun fethData(): MediatorLiveData<BaseFethData> {


        val mediatior= MediatorLiveData<BaseFethData>()

        mediatior.addSource(loader){
            if (it!=null)
                mediatior.value = BaseFethData.Loader(it)
        }

        mediatior.addSource(success){
            if (it!=null)
                mediatior.value = BaseFethData.Success(it)
        }

        mediatior.addSource(error){

            if (it!=null)
                mediatior.value = BaseFethData.Error(it)

        }
        return mediatior
    }


    open   fun<T> setupSubscribe(observable: Observable<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null) =

        disposable.add(SubscribeUtils.setupSubscribe(observable,onSuccess,onError))

    open   fun<T> setupSubscribe(observable: Flowable<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null) =

        disposable.add(SubscribeUtils.setupSubscribe(observable,onSuccess,onError))
    open   fun<T> setupSubscribe(observable: Maybe<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null) =

        disposable.add(SubscribeUtils.setupSubscribe(observable,onSuccess,onError))
    open   fun<T> setupSubscribe(observable: Single<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null) =

        disposable.add(SubscribeUtils.setupSubscribe(observable,onSuccess,onError))
    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}