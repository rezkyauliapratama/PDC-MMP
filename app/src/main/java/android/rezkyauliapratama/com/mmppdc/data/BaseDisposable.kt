package android.rezkyauliapratama.com.mmppdc.data

import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

abstract class BaseDisposable : AnkoLogger {

    var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }
    open fun onStop(){
        error { "onstop to clear compositedisposable" }
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}