package android.rezkyauliapratama.com.mmppdc.data.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class ApprovalSchema (
        var approve_status : String,
        var so : List<String>
) : Parcelable