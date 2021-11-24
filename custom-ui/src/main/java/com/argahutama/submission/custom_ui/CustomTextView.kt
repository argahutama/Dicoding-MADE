package com.argahutama.submission.custom_ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var size: Int = 0
        set(value) {
            field = value
            when (value) {
                B30 -> setTextSizeAndLineHeight(30, R.dimen.bold_30_line_height)
                B24 -> setTextSizeAndLineHeight(24, R.dimen.bold_24_line_height)
                B20 -> setTextSizeAndLineHeight(20, R.dimen.bold_20_line_height)
                B18 -> setTextSizeAndLineHeight(18, R.dimen.bold_18_line_height)
                B16 -> setTextSizeAndLineHeight(16, R.dimen.bold_16_line_height)
                B14 -> setTextSizeAndLineHeight(14, R.dimen.bold_14_line_height)
                S16 -> setTextSizeAndLineHeight(16, R.dimen.normal_16_line_height)
                S14 -> setTextSizeAndLineHeight(14, R.dimen.normal_14_line_height)
                S12 -> setTextSizeAndLineHeight(12, R.dimen.normal_12_line_height)
            }
        }

    private fun setTextSizeAndLineHeight(newTextSize: Int, @DimenRes lineHeightRes: Int) {
        textSize = newTextSize.toFloat()
        lineHeight = context.resources.getDimensionPixelSize(lineHeightRes)
    }

    init {
        val defaultColor = ContextCompat.getColor(context, R.color.black_900)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextView,
            0,
            0
        ).apply {
            try {
                size = getInteger(R.styleable.CustomTextView_size, 0)
                val textColor =
                    getColor(R.styleable.CustomTextView_android_textColor, defaultColor)
                setTextColor(textColor)
            } finally {
                recycle()
            }
        }
        letterSpacing = 0f

        if (!isInEditMode) {
            if (size <= 5) setTypeface(Companion.getTypeface(context), Typeface.BOLD)
            else setTypeface(Companion.getTypeface(context), Typeface.NORMAL)
        } else {
            if (size <= 5) setTypeface(typeface, Typeface.BOLD)
            else setTypeface(typeface, Typeface.NORMAL)
        }
    }

    companion object {
        private var typefaceInstance: Typeface? = null
        fun getTypeface(context: Context) = typefaceInstance ?: run {
            typefaceInstance = ResourcesCompat.getFont(context, R.font.effra)
            typefaceInstance
        }

        const val B30 = 0
        const val B24 = 1
        const val B20 = 2
        const val B18 = 3
        const val B16 = 4
        const val B14 = 5
        const val S16 = 6
        const val S14 = 7
        const val S12 = 8
    }
}