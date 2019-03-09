package com.kashanok.classes.homework.hw4

import android.content.Context
import android.graphics.* // ktlint-disable no-wildcard-imports
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.random.Random

class DiagramView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    companion object {
        var sections = ArrayList<Int>()
    }

    private val defaultData = "5, 5, 5, 5, 2, 11"
    private var paint: Paint? = null
    private var centerX: Float = 0F
    private var centerY: Float = 0F
    private var textSize: Float = 0F

    private var isInitialized: Boolean = false

    private fun initData() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        if (sections.isNullOrEmpty()) setNewData(defaultData)
        isInitialized = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isInitialized) initData()

        drawSector(canvas)
    }

    private fun drawSector(canvas: Canvas?) {
        var overallSum = 0
        sections.forEach { overallSum += it }
        val numAnglePairsList = ArrayList<Pair<Int, Double>>()
        sections.forEach { numAnglePairsList.add(Pair(it, it * 360.0 / overallSum)) }

        paint?.let {
            it.isAntiAlias = true
            it.isDither = true
            it.style = Paint.Style.FILL
            val min = Math.min(width, height)
            val baseMargin = (min / 6).toFloat()
            val topMargin = (baseMargin * 1.5).toFloat()
            val endRight = baseMargin + (min - 2 * baseMargin)
            val endBottom = topMargin + (min - 2 * baseMargin)
            val diagramRadius = (endRight - baseMargin) / 2

            val rectF = RectF(baseMargin, topMargin, endRight, endBottom)
            centerX = rectF.centerX()
            centerY = rectF.centerY()
            var segStartPoint = 0F
            for (i in 0 until numAnglePairsList.size) {
                it.strokeWidth = 8F
                it.color =
                    Color.argb(255, numAnglePairsList[i].second.toInt(), Random.nextInt(256), Random.nextInt(256))
                canvas?.drawArc(rectF, segStartPoint, numAnglePairsList[i].second.toFloat(), true, it)
                drawFootnote(
                    canvas,
                    it,
                    diagramRadius,
                    segStartPoint + numAnglePairsList[i].second.toFloat() / 2,
                    numAnglePairsList[i].first
                )
                segStartPoint += numAnglePairsList[i].second.toFloat()
            }
        }
    }

    private fun drawFootnote(canvas: Canvas?, paint: Paint, diagramRadius: Float, lineAngle: Float, diagramText: Int) {
        var startLine = calculateCoordinates(FootnoteType.ROW_START, diagramRadius, lineAngle)
        var endLine = calculateCoordinates(FootnoteType.ROW_END, diagramRadius, lineAngle)
        var footnoteText = calculateTextCoordinates(FootnoteType.TEXT, diagramRadius, lineAngle)

        paint.color = Color.GRAY
        canvas?.drawLine(startLine.first, startLine.second, endLine.first, endLine.second, paint)
        drawFootnoteCenter(paint, canvas, endLine.first, endLine.second)
        drawFootnoteText(paint, canvas, diagramText, footnoteText.first, footnoteText.second)
    }

    private fun drawFootnoteCenter(paint: Paint, canvas: Canvas?, xToEndLine: Float, yToEndLine: Float) {
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(xToEndLine, yToEndLine, 12F, paint)
    }

    private fun drawFootnoteText(paint: Paint, canvas: Canvas?, text: Int, x: Float, y: Float) {
        textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 20F,
            resources.displayMetrics
        )
        paint.textSize = textSize
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.color = Color.BLACK
        val rect = Rect()
        val footnoteText = text.toString()
        paint.getTextBounds(footnoteText, 0, footnoteText.length, rect)
        canvas?.drawText(footnoteText, x, y, paint)
    }

    private fun calculateCoordinates(type: FootnoteType, diagramRadius: Float, lineAngle: Float): Pair<Float, Float> {
        return Pair(
            centerX + (Math.cos(Math.toRadians(lineAngle.toDouble())) * (diagramRadius + type.addition)).toFloat(),
            centerY + (Math.sin(Math.toRadians(lineAngle.toDouble())) * (diagramRadius + type.addition)).toFloat()
        )
    }

    private fun calculateTextCoordinates(type: FootnoteType, radius: Float, angle: Float): Pair<Float, Float> {
        return when {
            angle < 180 -> {
                Pair(
                    centerX + (Math.cos(Math.toRadians(angle.toDouble())) * (radius + type.addition)).toFloat(),
                    centerY + (Math.sin(Math.toRadians(angle.toDouble())) * (radius + type.addition)).toFloat() + type.addition
                )
            }
            else -> {
                Pair(
                    centerX + (Math.cos(Math.toRadians(angle.toDouble())) * (radius + type.addition)).toFloat(),
                    centerY + (Math.sin(Math.toRadians(angle.toDouble())) * (radius + type.addition)).toFloat() - type.addition + textSize / 2
                )
            }
        }
    }

    fun setNewData(data: String?) {
        data?.let {
            sections.clear()
            data.split("[, ?.@]+".toRegex()).forEach { sections.add(it.toInt()) }
        }
        isInitialized = false
        invalidate()
    }
}

enum class FootnoteType(val addition: Float) {
    ROW_START(0F), ROW_END(50F), TEXT(60F)
}