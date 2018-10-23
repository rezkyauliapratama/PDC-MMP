package android.rezkyauliapratama.com.mmppdc.data.EventBus

import io.reactivex.Observable

interface EventBusInterface {
    fun post(event: Any)

    fun <T> observable(eventClass: Class<T>): Observable<T>
}
