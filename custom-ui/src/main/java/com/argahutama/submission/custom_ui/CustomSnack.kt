package com.argahutama.submission.custom_ui

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar

object CustomSnack {
    private const val SUCCESS = 0
    private const val WARNING = 1
    private const val FAILED = 2

    fun success(context: Context, message: String, root: View) =
        setupView(context, message, SUCCESS, root)

    fun warning(context: Context, root: View, message: String) =
        setupView(context, message, WARNING, root)

    fun failed(context: Context, root: View, message: String) =
        setupView(context, message, FAILED, root)

    private fun setupView(context: Context, message: String, type: Int, root: View) {
        val view = View.inflate(context, R.layout.layout_custom_snack, null)
        view.findViewById<TextView>(R.id.ctvSnackMessage).text =
            HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val drawable = when (type) {
            SUCCESS -> R.drawable.ic_round_check_24
            WARNING -> R.drawable.ic_round_warning_24
            else -> R.drawable.ic_round_clear_24
        }

        val textColor = when (type) {
            FAILED -> R.color.white
            else -> R.color.black_900
        }

        view.findViewById<ImageView>(R.id.ivSnackIcon)
            .setImageDrawable(ContextCompat.getDrawable(context, drawable))
        view.findViewById<CustomTextView>(R.id.ctvSnackMessage)
            .setTextColor(textColor)

        val snackBar = Snackbar.make(root, message, Snackbar.LENGTH_SHORT)

        val margin = (30 * Resources.getSystem().displayMetrics.density).toInt()
        snackBar.view.setPadding(0, 0, 0, margin)
        snackBar.view.elevation = 0f

        (snackBar.view as ViewGroup).run {
            removeAllViews()
            addView(view)
        }

        snackBar.setBackgroundTint(ContextCompat.getColor(context, android.R.color.transparent))
        snackBar.show()
    }
}