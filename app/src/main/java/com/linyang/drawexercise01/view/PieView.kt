package com.linyang.drawexercise01.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.px
private val ANGLES = floatArrayOf(60f, 100f, 120f, 80f)
private val COLORS = listOf(Color.parseColor("#2879FD"), Color.parseColor("#C1175C"),
    Color.parseColor("#009688"), Color.parseColor("#FF8E00"))
private val OFFSET_LENGTH = 20f.px
private val INDEX = 2
class PieView(context: Context?, attrs: AttributeSet?) : View (context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {

            paint.color = COLORS[index]
            if (index == INDEX) {
                canvas.save()
                canvas.translate(OFFSET_LENGTH * cos(Math.toRadians((angle/2f+startAngle).toDouble())).toFloat(),
                    OFFSET_LENGTH * sin(Math.toRadians((angle/2f+startAngle).toDouble())).toFloat())
            }
            canvas.drawArc(width/2f-RADIUS, height/2f -RADIUS,
                width/2f+RADIUS, height/2f+RADIUS,
                startAngle, angle, true, paint)
            if (index == INDEX) canvas.restore()
            startAngle += angle
        }
    }
}