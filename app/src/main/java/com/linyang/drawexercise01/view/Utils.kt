package com.linyang.drawexercise01.view

import android.content.res.Resources
import android.util.TypedValue

class Utils {
    fun dp2px(value: Float) : Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
    }
}