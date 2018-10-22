package android.rezkyauliapratama.com.mmppdc.utils

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class FormatNumber @Inject constructor(){

    fun formatThousandSeparator(s: String) : String{
        return try {
            // The comma in the format specifier does the trick
            String.format("%,d", s.toLong())
        } catch (e: Exception) {
            s
        }
    }

    fun formatDecimal(s: String): String{
        return try {
            // The comma in the format specifier does the trick
            DecimalFormat("##.##").format(s.toDouble())
        } catch (e: Exception) {
            s
        }
    }
}