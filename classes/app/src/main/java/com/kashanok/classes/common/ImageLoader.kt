package com.kashanok.classes.common

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kashanok.classes.R

object ImageLoader {

    fun loadImage(target: ImageView, url: String) {
        target?.let {
            Glide.with(it.context)
                .load(url)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        setNoPictureImage(it)
                        return false
                    }
                })
                .into(it)
        }
    }

    private fun setNoPictureImage(view: ImageView) {
        val drawable = view.context.getDrawable(R.drawable.icon_test)
        drawable?.setTint(ContextCompat.getColor(view.context, R.color.colorGray))
        view.setImageDrawable(drawable)
    }

    fun clearArea(imageView: ImageView) {
        Glide.with(imageView.context).clear(imageView)
    }
}