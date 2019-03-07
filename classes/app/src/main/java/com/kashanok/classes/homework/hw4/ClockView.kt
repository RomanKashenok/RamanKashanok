package com.kashanok.classes.homework.hw4

import android.content.Context
import android.graphics.* // ktlint-disable no-wildcard-imports
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.* // ktlint-disable no-wildcard-imports

class ClockView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var paint: Paint? = null

    private var centerX: Float = 0F
    private var centerY: Float = 0F
    private var clockRadius: Float = 0F
    private var oneMinuteDegree: Float = 0F
    private var defaultTextSize: Float = 0F

    private var hourLineLength = 0
    private var hourStrokeWidth = 0F
    private var hourHandLength: Double = 0.0

    private var minuteLineLength = 0
    private var minuteStrokeWidth = 0F
    private var minuteHandLength: Double = 0.0
    private var secondHandLength: Double = 0.0

    private var isInitialized: Boolean = false

    private fun initData() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
        clockRadius = Math.min(centerX, centerY)
        oneMinuteDegree = 6F
        defaultTextSize = 20F

        hourLineLength = 40
        hourStrokeWidth = 6F
        hourHandLength = clockRadius / 1.8

        minuteLineLength = 20
        minuteStrokeWidth = 3F
        minuteHandLength = clockRadius / 1.5
        secondHandLength = clockRadius / 1.2
        isInitialized = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isInitialized) initData()

        drawClock(canvas)
        drawClockDashes(canvas)
        drawArrows(canvas)
        drawHourNumbers(canvas)

        postInvalidateDelayed(900)
        invalidate()
    }

    private fun drawClock(canvas: Canvas?) {
        paint?.let {
            it.color = resources.getColor(com.kashanok.classes.R.color.colorGrayDark)
            canvas?.drawCircle(centerX, centerY, clockRadius, it)
        }
    }

    private fun drawClockDashes(canvas: Canvas?) {
        paint?.let {
            for (i in 0..59) {
                it.color = Color.WHITE
                var lineLength = minuteLineLength
                it.strokeWidth = minuteStrokeWidth
                if (i % 5 == 0) {
                    lineLength = hourLineLength
                    it.strokeWidth = hourStrokeWidth
                }
                canvas?.drawLine(centerX, centerY - clockRadius + lineLength, centerX, centerY - clockRadius, it)
                canvas?.rotate(oneMinuteDegree, centerX, centerY)
            }
        }
    }

    private fun drawArrows(canvas: Canvas?) {
        paint?.let {
            it.color = Color.YELLOW
            it.strokeWidth = hourStrokeWidth
            val c = Calendar.getInstance()
            drawHand(
                canvas,
                (c.get(Calendar.HOUR) + c.get(Calendar.MINUTE) / 60) * 5f.toDouble(),
                ClockHandType.HOUR,
                it
            )
            it.strokeWidth = minuteStrokeWidth
            drawHand(canvas, c.get(Calendar.MINUTE).toDouble(), ClockHandType.MINUTE, it)
            drawHand(canvas, c.get(Calendar.SECOND).toDouble(), ClockHandType.SECOND, it)
        }
    }

    private fun drawHand(canvas: Canvas?, loc: Double, type: ClockHandType, paint: Paint) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = when (type) {
            ClockHandType.HOUR -> { hourHandLength }
            ClockHandType.MINUTE -> { minuteHandLength }
            ClockHandType.SECOND -> { secondHandLength }
        }
        canvas?.drawLine(
            centerX, centerY,
            (centerX + Math.cos(angle) * handRadius).toFloat(),
            (centerY + Math.sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawHourNumbers(canvas: Canvas?) {
        paint?.let {
            val fontSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, defaultTextSize,
                resources.displayMetrics
            ).toInt()
            it.textSize = fontSize.toFloat()
            it.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            it.color = Color.WHITE
            val rect = Rect()
            for (number in 3..12 step 3) {
                val tmp = number.toString()
                it.getTextBounds(tmp, 0, tmp.length, rect)
                val angle = number * 30.0
                val x = centerX - rect.width() / 2 + Math.sin(Math.toRadians(angle)) * (secondHandLength - defaultTextSize)
                val y = centerY + + rect.height() / 2 - Math.cos(Math.toRadians(angle)) * (secondHandLength - defaultTextSize)
                canvas?.drawText(tmp, x.toFloat(), y.toFloat(), it)
            }
        }
    }

    private enum class ClockHandType {
        HOUR, MINUTE, SECOND
    }
}