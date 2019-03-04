package com.kashanok.classes.classwork.cw4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView : View {

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var pointX: Float = 0f
    private var pointY: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawThreeSquares(canvas)
        drawArch(canvas)
        canvas?.drawCircle(pointX, pointY, 80f, paint)
    }

    private fun drawThreeSquares(canvas: Canvas?) {
        paint.color = Color.BLUE
        val squareSide = width / 4
        val distanceBetween = squareSide / 4
        var fromLeft = distanceBetween
        for (i in 1..3) {
            drawSquare(canvas, squareSide.toFloat(), fromLeft.toFloat(), distanceBetween.toFloat())
            fromLeft += (distanceBetween + squareSide)
        }
    }

    private fun drawSquare(canvas: Canvas?, squareSide: Float, left: Float, top: Float) {
        val rectF = RectF(left, top, left + squareSide, top + squareSide)
        canvas?.drawRect(rectF, paint)
    }

    private fun drawArch(canvas: Canvas?) {
        val squareSide: Float = (width / 2.1).toFloat()
        val topMargin: Float = (height / 2).toFloat()

        val rectF = RectF((width / 2) - squareSide, topMargin, squareSide * 2, topMargin + squareSide * 2)

        paint.color = Color.RED
        canvas?.drawArc(rectF, 180f, 180f, true, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_MOVE) {
            pointX = event.x
            pointY = event.y
            invalidate()
        }
        return true
    }
}