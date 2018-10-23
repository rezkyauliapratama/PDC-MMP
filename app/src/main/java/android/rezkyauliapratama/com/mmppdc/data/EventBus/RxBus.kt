package android.rezkyauliapratama.com.mmppdc.data.EventBus

import io.reactivex.Observable
import io.reactivex.functions.Predicate
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxBus
@Inject constructor() : EventBusInterface {

    private val mBusSubject = PublishSubject.create<Any>()


    override fun post(event: Any) {
        if (this.mBusSubject.hasObservers()) {
            this.mBusSubject.onNext(event)

        }
    }

    override fun <T> observable(eventClass: Class<T>): Observable<T> {
        return this.mBusSubject
                .filter { o -> o != null } // Filter out null objects, better safe than sorry
                .filter(Predicate<Any> { eventClass.isInstance(it) }) // We're only interested in a specific event class
                .cast(eventClass) // Cast it for easier usage
    }


}
