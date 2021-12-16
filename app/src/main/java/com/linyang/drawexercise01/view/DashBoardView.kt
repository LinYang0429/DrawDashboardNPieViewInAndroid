package com.linyang.drawexercise01.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.px
private val MARK = 5
private val LENGTH = 120f.px
private const val OPEN_ANGLE = 120
private val DASH_WIDTH = 5f.px
private val DASH_LENGTH = 10f.px
class DashBoardView(context: Context?, attrs: AttributeSet?) : View (context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private lateinit var pathEffect: PathEffect

    init {
        paint.strokeWidth = 3f.px
        paint.style = Paint.Style.STROKE
        dash.addRect(0f,0f, DASH_WIDTH,DASH_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(width/2f-RADIUS, height/2f -RADIUS, width/2f+RADIUS, height/2f+RADIUS,
            90 + OPEN_ANGLE/2f, 360f- OPEN_ANGLE)
        val dashPhase = PathMeasure(path, false).length - DASH_WIDTH
        pathEffect = PathDashPathEffect(dash, dashPhase / 20f, 0f, PathDashPathEffect.Style.ROTATE)

    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawPath(path, paint)
        paint.setPathEffect(pathEffect)
        canvas.drawArc(width/2f-150f.px, height/2f -150f.px, width/2f+150f.px, height/2f+150f.px,
            90 + OPEN_ANGLE/2f, 360f- OPEN_ANGLE, false, paint)
        paint.setPathEffect(null)
        // 5
        canvas.drawLine(width/2f, height/2f,
            width/2f + LENGTH * cos(markToRadius(MARK)).toFloat(),
            height/2f + LENGTH * sin(markToRadius(MARK)).toFloat(),
            paint)
    }

    private fun markToRadius(mark: Int) =
        Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE).toFloat() / 20 * 5f).toDouble())
}