package android.rezkyauliapratama.com.mmppdc.data.schema

/**
 * Created by Rezky Aulia Pratama on 16/10/18.
 */
sealed class Attr {
    val isExpandable: Boolean = false
    val totalItem: Int = 0
}

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
        val itemSchemas: List<ItemSchema>
): Attr()