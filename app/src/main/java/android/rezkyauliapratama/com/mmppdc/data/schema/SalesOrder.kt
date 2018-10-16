package android.rezkyauliapratama.com.mmppdc.data.schema

/**
 * Created by Rezky Aulia Pratama on 16/10/18.
 */
data class SalesOrder(
        val id: String,
        val so_number: String,
        val customer_code :String,
        val customer_name : String,
        val customer_category : String,
        val customer_type : String,
        val total_credit : String,
        val credit_term : String
)