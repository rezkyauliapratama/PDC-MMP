package android.rezkyauliapratama.com.mmppdc.data.repository

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.network.api.SoApi
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PdcUseCase @Inject constructor(val dataManager: DataManager) : ApiObservable<PdcUseCase.Listener>() {

    interface Listener {
        fun onFetchPdcSuccess(response: List<PdcSchema>)
        fun onFetchPdcFailure(message: String)
    }

    fun PdcWaitingStatusAndNotify(){
        dataManager.api
                .so
                .getSoWaiting()
                .subscribeOn(Schedulers.io())
                .flatMap{t ->
                    Single.just(t.so)
                }
                .map { it ->
                    for (pdc: PdcSchema in it){
                        pdc.totalItem = pdc.items.size
                    }
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    notifySuccess(response)
                }) { throwable ->
                    notifyFailure(throwable.localizedMessage)
                }
    }

    private fun notifyFailure(message: String) {
        for (listener in listeners) {
            listener.onFetchPdcFailure(message)
        }
    }

    private fun notifySuccess(response :List<PdcSchema>) {

        for (listener in listeners) {
            listener.onFetchPdcSuccess(response)
        }
    }



}