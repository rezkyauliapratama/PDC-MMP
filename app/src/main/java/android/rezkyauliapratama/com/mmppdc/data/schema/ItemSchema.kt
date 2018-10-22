package android.rezkyauliapratama.com.mmppdc.data.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rezky Aulia Pratama on 16/10/18.
 */
@Parcelize
data class ItemSchema (
        var id : String,
        var so_id : String,
        var item_code : String,
        var item_name : String,
        var discount_on : String,
        var discount_bp1 : String,
        var discount_bp2 : String,
        var discount_total : String,
        var discount_max : String,
        var hpp_cab : String,
        var unit : String,
        var hna_per_pcs : String,
        var total_hna : String,
        var description : String
) : Parcelable