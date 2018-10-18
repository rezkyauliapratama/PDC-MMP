package android.rezkyauliapratama.com.mmppdc.data.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSchema(
        var id: String = "",
        val name: String = "",
        val email: String,
        val pin: String = "",
        val password: String
) : Parcelable