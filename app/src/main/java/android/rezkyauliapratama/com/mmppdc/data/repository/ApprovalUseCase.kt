package android.rezkyauliapratama.com.mmppdc.data.repository

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.network.api.SoApi
import android.rezkyauliapratama.com.mmppdc.data.schema.ApprovalSchema
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error
import javax.inject.Inject

class ApprovalUseCase @Inject constructor(val dataManager: DataManager) : ApiObservable<ApprovalUseCase.Listener>() {

    interface Listener {
        fun onFetchApprovalSuccess(response: SoApi.ApprovalResponse)
        fun onFetchApprovalFailure(message: String)
    }

    fun approvalAndNotify(request: ApprovalSchema){
        error { "approval req: ${Gson().toJson(request)}" }
        dataManager.api
                .so
                .postSoApproval(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    notifySuccess(response)
                }) { throwable ->
                    notifyFailure(throwable.localizedMessage)
                }
    }

    private fun notifyFailure(message: String) {
        for (listener in listeners) {
            listener.onFetchApprovalFailure(message)
        }
    }

    private fun notifySuccess(response :SoApi.ApprovalResponse) {

        for (listener in listeners) {
            listener.onFetchApprovalSuccess(response)
        }
    }

}
