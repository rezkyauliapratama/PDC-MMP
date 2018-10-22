package android.rezkyauliapratama.com.mmppdc.data.repository

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.schema.ItemSchema
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.utils.FormatNumber
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PdcUseCase @Inject constructor(val dataManager: DataManager, val formatNumber: FormatNumber) : ApiObservable<PdcUseCase.Listener>() {

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
                        for (item: ItemSchema in pdc.items){
                            changeFormat(item)
                        }
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

    fun PdcHistoryAndNotify(){
        dataManager.api
                .so
                .getSoHistory()
                .subscribeOn(Schedulers.io())
                .flatMap{t ->
                    Single.just(t.so)
                }
                .map { it ->
                    for (pdc: PdcSchema in it){
                        pdc.totalItem = pdc.items.size
                        for (item: ItemSchema in pdc.items){
                            changeFormat(item)
                        }
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

    private fun changeFormat(item : ItemSchema): ItemSchema{
        item.discount_on = if(item.discount_on.isNotEmpty()) formatNumber.formatDecimal(item.discount_on) else item.discount_on
        item.discount_bp1 = if(item.discount_bp1.isNotEmpty()) formatNumber.formatDecimal(item.discount_bp1) else item.discount_bp1
        item.discount_bp2 = if(item.discount_bp2.isNotEmpty()) formatNumber.formatDecimal(item.discount_bp2) else item.discount_bp2
        item.discount_total = if(item.discount_total.isNotEmpty()) formatNumber.formatDecimal(item.discount_total) else item.discount_total
        item.discount_max = if(item.discount_max.isNotEmpty()) formatNumber.formatDecimal(item.discount_max) else item.discount_max

        item.hpp_cab= if(item.hpp_cab.isNotEmpty()) formatNumber.formatThousandSeparator(item.hpp_cab) else item.hpp_cab
        item.unit = if(item.unit.isNotEmpty()) formatNumber.formatThousandSeparator(item.unit) else item.unit
        item.hna_per_pcs = if(item.hna_per_pcs.isNotEmpty()) formatNumber.formatThousandSeparator(item.hna_per_pcs) else item.hna_per_pcs
        item.total_hna = if(item.total_hna.isNotEmpty()) formatNumber.formatThousandSeparator(item.total_hna) else item.total_hna
        return item
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