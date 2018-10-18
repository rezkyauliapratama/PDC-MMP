package android.rezkyauliapratama.com.mmppdc.screens.common.controller

import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger

abstract class BaseController : AnkoLogger {

    var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }
    open fun onStop(){
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}