package android.rezkyauliapratama.com.mmppdc.data.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


open class ApiResponse<T> {

    @SerializedName(value = "result")
    @Expose
    var ApiResult: Boolean? = null

    @SerializedName(value = "ApiStatus")
    @Expose
    var ApiStatus: String? = null

    @SerializedName(value = "ApiMessage")
    @Expose
    var ApiMessage: String? = null

    @SerializedName("ApiElapsed")
    @Expose
    var ApiElapsed: String? = null

    @SerializedName(value = "ApiList", alternate = arrayOf("data"))
    @Expose
    var ApiList: List<T> = ArrayList()

    @SerializedName(value = "ApiValue")
    @Expose
    var ApiValue: T? = null

    @SerializedName(value = "id", alternate = arrayOf("Id"))
    @Expose
    var Id: Long = 0

    @SerializedName(value = "success", alternate = arrayOf("Success"))
    @Expose
    var Succcess = false
}
