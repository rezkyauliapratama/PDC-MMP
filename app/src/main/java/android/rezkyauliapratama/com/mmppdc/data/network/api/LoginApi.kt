package android.rezkyauliapratama.com.mmppdc.data.network.api

import android.os.Parcelable
import android.rezkyauliapratama.com.mmppdc.data.network.ObjectUrl
import android.rezkyauliapratama.com.mmppdc.data.schema.UserSchema
import com.google.gson.Gson
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class LoginApi  @Inject constructor(private val networkClient: NetworkClient) : AnkoLogger {
    val TAG : String  = LoginApi::class.java.simpleName

    //public function yang digunakan aplikasi untuk mengambil data articles dari API dengan cara menjalankan getAllArticles
    fun doLogin(user: UserSchema) : Single<Response> {
        return Single.create<Response> { emitter ->
            try {
                login(user)
                        .apply { this?.let { emitter.onSuccess(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }


    private fun login(user: UserSchema) : Response?
    {

        try
        {
            error { "user : ${Gson().toJson(user)}" }
            return with(networkClient){
                cancelByTag(TAG)
                withUrl(ObjectUrl.login())
                        .initAs(Response::class.java)
                        .setJsonPojoBody(user)
                        .setTag(TAG)
                        .syncFuture
            }
        } catch (e: Exception) {
            throw e
        }

    }


    @Parcelize
    data class Response (
            val user: UserSchema,
            val token: String
    ) : Parcelable
}