package com.kashanok.classes.homework.hw3

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_hw3.*
import org.apache.commons.validator.routines.UrlValidator

class LoadPictureActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoadPictureActivity::class.java)
        }
        const val RANDOM_PICTURE_REPOSITORY_URL = "https://picsum.photos/300/500/?random"
        const val URL_PREFIX = "http://"
    }

    private var randomPicture: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kashanok.classes.R.layout.activity_hw3)

        defaultImageTextView.setOnClickListener {
            randomPicture = true
            editTextView.text = null
            editTextView.hint = getString(com.kashanok.classes.R.string.random_picture_selected)
        }

        uploadButton.setOnClickListener {
            pictureView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE

            var imageUri = editTextView.text.toString()

            when {
                randomPicture -> {
                    loadWithGlide(pictureView, RANDOM_PICTURE_REPOSITORY_URL)
                }

                !imageUri.isNullOrBlank() -> {
                    if (!imageUri.startsWith(URL_PREFIX)) {
                        imageUri = URL_PREFIX + imageUri
                    }
                    if (UrlValidator().isValid(imageUri)) {
                        loadWithGlide(pictureView, imageUri)
                    } else {
                        onInvalidUrlAction()
                    }
                }
                else -> {
                    onInvalidUrlAction()
                }
            }
            randomPicture = false
            editTextView.hint = getString(R.string.enter_uri)
        }
    }

    private fun loadWithGlide(target: ImageView?, imageUri: String) {
        target?.let { it ->
            Glide.with(this)
                .load(imageUri)
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
                        toggleVisibilityOnLoad()
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        setNoPictureImage()
                        toggleVisibilityOnLoad()
                        return false
                    }
                })
                .into(it)
        }
    }

    private fun toggleVisibilityOnLoad() {
        pictureView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun setNoPictureImage() {
        val drawable = getDrawable(R.drawable.icon_test)
        drawable?.setTint(ContextCompat.getColor(this@LoadPictureActivity, R.color.colorGray))
        pictureView.setImageDrawable(drawable)
    }

    private fun showInvalidUrlToast() {
        val toast = Toast.makeText(
            this,
            getString(com.kashanok.classes.R.string.error_invalid_url),
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    private fun onInvalidUrlAction() {
        showInvalidUrlToast()
        setNoPictureImage()
        toggleVisibilityOnLoad()
    }
}
