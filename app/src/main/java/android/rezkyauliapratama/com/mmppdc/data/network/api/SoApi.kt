package android.rezkyauliapratama.com.mmppdc.data.network.api

import android.os.Parcelable
import android.rezkyauliapratama.com.mmppdc.data.network.ObjectUrl
import android.rezkyauliapratama.com.mmppdc.data.repository.ApiResponse
import android.rezkyauliapratama.com.mmppdc.data.schema.ApprovalSchema
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import com.google.gson.Gson
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class SoApi @Inject constructor(private val networkClient: NetworkClient) : BaseApi(),AnkoLogger {


    //public function yang digunakan aplikasi untuk mengambil data articles dari API dengan cara menjalankan getAllArticles
    fun getSoWaiting() : Single<Response> {
      /*  return Single.create<Response> { emitter ->
            try {
                soWaiting()
                        .apply { this?.let { emitter.onSuccess(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }*/

        val tag = "soWaiting"

            return with(networkClient){
                cancelByTag(tag)
                withUrl(ObjectUrl.soWaiting())
                        .initAs(Response::class.java)
                        .setHeaders(getUserHeaderWithToken().build())
                        .setTag(tag)
                        .getAsSingle()
            }



    }

    fun getSoHistory() : Single<Response> {
        return Single.create<Response> { emitter ->
            try {
                soHistory()
                        .apply { this?.let { emitter.onSuccess(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }

    fun postSoApproval(request : ApprovalSchema) : Single<ApprovalResponse> {
        return Single.create<ApprovalResponse> { emitter ->
            try {
                soApproval(request)
                        .apply { this?.let { emitter.onSuccess(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }

    private fun soWaiting() : Response?
    {
        val tag = "soWaiting"
        try
        {
            return with(networkClient){
                cancelByTag(tag)
                withUrl(ObjectUrl.soWaiting())
                        .initAs(Response::class.java)
                        .setHeaders(getUserHeaderWithToken().build())
                        .setTag(tag)
                        .syncFuture
            }
        } catch (e: Exception) {
            throw e
        }

    }


    private fun soHistory() : Response?
    {
        val tag = "soHistory"
        try
        {
            return with(networkClient){
                cancelByTag(tag)
                withUrl(ObjectUrl.soHistory())
                        .initAs(Response::class.java)
                        .setHeaders(getUserHeaderWithToken().build())
                        .setTag(tag)
                        .syncFuture
            }
        } catch (e: Exception) {
            throw e
        }

    }

    private fun soApproval(request: ApprovalSchema) : ApprovalResponse?
    {
        val tag = "soApproval"
        try
        {
            return with(networkClient){
                cancelByTag(tag)
                withUrl(ObjectUrl.soApproval())
                        .initAs(ApprovalResponse::class.java)
                        .setHeaders(getUserHeaderWithToken().build())
                        .setJsonPojoBody(request)
                        .setTag(tag)
                        .syncFuture
            }
        } catch (e: Exception) {
            throw e
        }

    }


    class ApprovalResponse: ApiResponse<List<PdcSchema>>()

    @Parcelize
    data class Response (
            val so: List<PdcSchema>
    ) : Parcelable
}
