package com.argahutama.submission.core.util

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideListener(
    private val imageView: View? = null,
    private val shimmer: View? = null,
    private val onReadyCallback: (() -> Unit)? = null,
    private val onFailedCallback: (() -> Unit)? = null,
) : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean,
    ): Boolean {
        shimmer?.visibility = View.INVISIBLE
        onFailedCallback?.let { it() }
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean,
    ): Boolean {
        imageView?.visibility = View.VISIBLE
        shimmer?.visibility = View.INVISIBLE
        onReadyCallback?.let { it() }
        return false
    }
}
