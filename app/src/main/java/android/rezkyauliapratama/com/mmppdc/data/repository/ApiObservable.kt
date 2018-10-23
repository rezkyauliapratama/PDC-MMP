package android.rezkyauliapratama.com.mmppdc.data.repository

import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Rezky Aulia Pratama on 1/10/18.
 */

abstract class ApiObservable<LISTENER_CLASS> : AnkoLogger {


    lateinit var compositeDisposable: CompositeDisposable

    // thread-safe set of listeners
    private val mListeners = Collections.newSetFromMap(
            ConcurrentHashMap<LISTENER_CLASS, Boolean>(1))

    protected val listeners: Set<LISTENER_CLASS>
        get() = Collections.unmodifiableSet(mListeners)


    fun registerListener(listener: LISTENER_CLASS) {
        mListeners.add(listener)
        compositeDisposable = CompositeDisposable()
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        error { "unregisterlistener" }
        mListeners.remove(listener)
        compositeDisposable.dispose()
    }

}
