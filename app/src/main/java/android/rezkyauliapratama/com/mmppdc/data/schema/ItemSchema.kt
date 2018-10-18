package android.rezkyauliapratama.com.mmppdc.data.schema

/**
 * Created by Rezky Aulia Pratama on 16/10/18.
 */
data class ItemSchema (
        val id : String,
        val so_id : String,
        val item_code : String,
        val item_name : String,
        val discoount_on : String,
        val discount_bp1 : String,
        val discount_bp2 : String,
        val discount_total : String,
        val discount_max : String,
        val hpp_cab : String,
        val unit : String,
        val hna_per_pcs : String,
        val total_hna : String,
        val description : String
)