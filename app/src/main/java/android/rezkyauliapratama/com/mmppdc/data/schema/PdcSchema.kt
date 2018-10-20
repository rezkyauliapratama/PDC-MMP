package android.rezkyauliapratama.com.mmppdc.data.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rezky Aulia Pratama on 16/10/18.
 */
sealed class Attr {
    var isExpanded: Boolean = false
    var totalItem: Int = 0
    var isSelected: Boolean = false
}

@Parcelize
data class PdcSchema(
        val id: String,
        val so_number: String,
        val customer_code :String,
        val customer_name : String,
        val customer_category : String,
        val customer_type : String,
        val total_credit : String,
        val credit_term : String,
        val approve_status: String,
        val user_email: String,
        val created_date: String,
        val approve_date: String,
        val erp_response: String,
        val items: List<ItemSchema>
): Attr(), Parcelable