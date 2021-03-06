package android.rezkyauliapratama.com.mmppdc.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import java.util.*
import java.util.regex.Pattern

class DimensionConverter {

    // -- Initialize dimension string to constant lookup.
    val dimensionConstantLookup = initDimensionConstantLookup()

    // -- Initialize pattern for dimension string.
    private val DIMENSION_PATTERN = Pattern.compile("^\\-?\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*")


    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    private fun initDimensionConstantLookup(): Map<String, Int> {
        val m = HashMap<String, Int>()
        m["px"] = TypedValue.COMPLEX_UNIT_PX
        m["dip"] = TypedValue.COMPLEX_UNIT_DIP
        m["dp"] = TypedValue.COMPLEX_UNIT_DIP
        m["sp"] = TypedValue.COMPLEX_UNIT_SP
        m["pt"] = TypedValue.COMPLEX_UNIT_PT
        m["in"] = TypedValue.COMPLEX_UNIT_IN
        m["mm"] = TypedValue.COMPLEX_UNIT_MM
        return Collections.unmodifiableMap(m)
    }

    fun stringToDimensionPixelSize(dimension: String, metrics: DisplayMetrics): Int {
        // -- Mimics TypedValue.complexToDimensionPixelSize(int data, DisplayMetrics metrics).
        val internalDimension = stringToInternalDimension(dimension)
        val value = internalDimension.value
        val f = TypedValue.applyDimension(internalDimension.unit, value, metrics)
        val res = (f + 0.5f).toInt()
        if (res != 0) return res
        if (value == 0f) return 0
        return if (value > 0) 1 else -1
    }

    fun stringToDimension(dimension: String, metrics: DisplayMetrics?): Float {
        // -- Mimics TypedValue.complexToDimension(int data, DisplayMetrics metrics).
        try {
            val internalDimension = stringToInternalDimension(dimension)
            return TypedValue.applyDimension(internalDimension.unit, internalDimension.value, metrics)
        } catch (e: Exception) {
            return 0f
        }

    }

    private fun stringToInternalDimension(dimension: String): InternalDimension {
        // -- Match target against pattern.
        val matcher = DIMENSION_PATTERN.matcher(dimension)
        if (matcher.matches()) {
            // -- Match found.
            // -- Extract value.
            val value = java.lang.Float.valueOf(matcher.group(1))!!.toFloat()
            // -- Extract dimension units.
            val unit = matcher.group(3).toLowerCase()
            // -- Get Android dimension constant.
            val dimensionUnit = dimensionConstantLookup[unit]
            return if (dimensionUnit == null) {
                // -- Invalid format.
                throw NumberFormatException()
            } else {
                // -- Return valid dimension.
                InternalDimension(value, dimensionUnit)
            }
        } else {
            // -- Invalid format.
            throw NumberFormatException()
        }
    }

    private inner class InternalDimension(internal var value: Float, internal var unit: Int)


}