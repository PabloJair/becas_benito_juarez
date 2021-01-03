package com.s10plus.core_application.utils

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


object SubscribeUtils {



    fun<T>  setupSubscribe(observable: Observable<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null): Disposable {


        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess,onError ?: Consumer {  Log.d("setupSubscribe","Error no emitido para el observable")})


    }

    fun<T>  setupSubscribe(observable: Flowable<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null, onCompletable: Action?=null): Disposable {
        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess,
                onError ?: Consumer { Log.d("setupSubscribe","Error no emitido para el Flowable") },
                onCompletable ?: Action { Log.d("setupSubscribe","completable no emitido para el Flowable")  })
    }




    fun<T>  setupSubscribe(observable: Maybe<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null, onCompletable: Action?=null): Disposable {

        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess,
                onError ?: Consumer { Log.d("setupSubscribe","Error no emitido para el Maybe") },
                onCompletable ?: Action {Log.d("setupSubscribe","Completable no emitido para el Maybe")  })


    }

    open fun<T>  setupSubscribe(observable: Single<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null): Disposable {

        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess,onError ?: Consumer { Log.d("setupSubscribe","Error no emitido para el Single") })


    }
}