package com.kashanok.classes.homework.hw4

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.diagram_drawing_view.view.*
import kotlinx.android.synthetic.main.owl_blinks_view.view.*
import kotlin.random.Random

class Hw4PagerAdapter(val context: Context) : PagerAdapter() {

    companion object {

        const val PAGES_COUNT = 3

        private const val OWL_BLINKING_VIEW = 0
        private const val CLOCK_VIEW = 1
        private const val DIAGRAM_VIEW = 2
    }

    private var handler: Handler = Handler()
    private var delay: Long = 0
    private lateinit var owlBlinker: Runnable

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        var page: View
        when (position) {
            OWL_BLINKING_VIEW -> {
                page = inflater.inflate(R.layout.owl_blinks_view, collection, false)
                setupOwlBlinks(page)
            }
            CLOCK_VIEW -> {
                page = inflater.inflate(R.layout.clock_drawing_view, collection, false)
            }
            DIAGRAM_VIEW -> {
                page = inflater.inflate(R.layout.diagram_drawing_view, collection, false)
                setupDiagramDataEnter(page)
            }
            else -> throw IllegalArgumentException()
        }

        collection.addView(page)
        return page
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == (p1 as View)
    }

    override fun getCount(): Int {
        return PAGES_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val titleId = when (position) {
            OWL_BLINKING_VIEW -> R.string.navigation_owl
            CLOCK_VIEW -> R.string.navigation_clock
            else -> throw IllegalStateException()
        }
        return context.getString(titleId)
    }

    private fun setupOwlBlinks(layout: View) {
        val animationDrawable = layout.owlBlinks.background as AnimationDrawable

        owlBlinker = Runnable {
            animationDrawable.stop()
            delay = Random.nextLong(Hw4Activity.MIN_DELAY, Hw4Activity.MAX_DELAY)
            handler.postDelayed(owlBlinker, delay)
            animationDrawable.start()
        }
        handler.post(owlBlinker)

        layout.owlSelector.setOnClickListener {
            layout.owlSelector.isActivated = !layout.owlSelector.isActivated
        }
    }

    private fun setupDiagramDataEnter(layout: View) {
        layout.diagramValuesButtonView.setOnClickListener {
            val data = layout.diagramValuesView.text.toString()
            layout.diagramView.setNewData(data)
        }
    }
}