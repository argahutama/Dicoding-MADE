package com.argahutama.submission.custom_ui

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged

class CustomTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var maxLength: Int = -1
        set(value) {
            if (value == -1) return
            field = value
            etContent.filters = arrayOf(InputFilter.LengthFilter(value))
        }

    private var minLine: Int = 1
        set(value) {
            field = value
            etContent.minLines = field
        }

    private var maxLine: Int = 1
        set(value) {
            field = value
            etContent.maxLines = field
        }

    private var prefixText: String? = null
        set(value) {
            if (value == null) return
            field = value

            flSuffix.visibility = View.GONE
            flPrefix.visibility = View.VISIBLE
            ivPrefix.visibility = View.GONE
            tvPrefix.apply {
                visibility = View.VISIBLE
                text = value
            }
        }

    private var prefixIcon: Drawable? = null
        set(value) {
            if (value == null) return
            field = value

            flPrefix.visibility = View.VISIBLE
            tvPrefix.visibility = View.GONE
            ivPrefix.apply {
                visibility = View.VISIBLE
                setImageDrawable(value)
            }
        }

    private var suffixIcon: Drawable? = null
        set(value) {
            if (value == null) return
            field = value

            flPrefix.visibility = View.GONE
            flSuffix.visibility = View.VISIBLE
            tvSuffix.visibility = View.GONE
            ivSuffix.apply {
                visibility = View.VISIBLE
                setImageDrawable(value)
            }
        }

    private var suffixText: String? = null
        set(value) {
            if (value == null) return
            field = value

            flSuffix.visibility = View.VISIBLE
            flPrefix.visibility = View.GONE
            ivSuffix.visibility = View.GONE
            tvSuffix.apply {
                visibility = View.VISIBLE
                text = value
            }
        }

    private var shouldBeEnabled: Boolean = true
        set(value) {
            field = value
            etContent.isEnabled = value

            if (value) reset()
            else disable()
        }

    private var hint: String? = null
        set(value) {
            if (value == null) return
            field = value
            etContent.hint = value
        }

    private var inputType: Int = 0
        set(value) {
            field = value
            etContent.inputType = value
        }

    private var label: String? = null
        set(value) {
            field = value

            if (value.isNullOrEmpty()) {
                tvLabel.visibility = View.GONE
            } else {
                tvLabel.visibility = View.VISIBLE
                tvLabel.text = value
            }
        }

    private var optional: Boolean = false
        set(value) {
            field = value
            tvOptional.visibility = if (value) View.VISIBLE else View.GONE
        }

    private var helper: String? = null
        set(value) {
            field = value

            if (value.isNullOrEmpty()) {
                tvHelper.visibility = View.GONE
            } else {
                tvHelper.visibility = View.VISIBLE
                tvHelper.text = value
            }
        }

    private var labelColor: Int = 0
        set(value) {
            field = value
            tvLabel.setTextColor(value)
        }

    private var optionalColor: Int = 0
        set(value) {
            field = value
            tvOptional.setTextColor(value)
        }

    private var helperColor: Int = 0
        set(value) {
            field = value
            tvHelper.setTextColor(value)
        }

    val text: String
        get() = etContent.text.toString()

    private var error: Boolean = false
        set(value) {
            field = value
            if (value) indicateError()
            else reset()
        }

    private var errorMessage: String? = null
        set(value) {
            field = value
            error = value != null
        }

    private var enableClear: Boolean = false
        set(value) {
            field = value
            if (!value) {
                etContent.doOnTextChanged { _, _, _, _ -> }
                ivAction.isVisible = false
            } else {
                etContent.doOnTextChanged { text, _, _, _ ->
                    ivAction.isVisible = text?.isNotEmpty() == true
                }
            }
        }

    private fun indicateError() {
        tfRoot.background = ContextCompat.getDrawable(context, R.drawable.bg_custom_text_field_error)
        val redColor = ContextCompat.getColor(context, R.color.ui_red)
        tvPrefix.setTextColor(redColor)
        etContent.setTextColor(redColor)

        if (errorMessage != null) {
            tvHelper.setTextColor(redColor)
            tvHelper.visibility = View.VISIBLE
            tvHelper.text = errorMessage
        }

        ivPrefix.setColorFilter(
            ContextCompat.getColor(context, R.color.ui_red),
            PorterDuff.Mode.SRC_IN
        )
        ivSuffix.setColorFilter(
            ContextCompat.getColor(context, R.color.ui_red),
            PorterDuff.Mode.SRC_IN
        )
    }

    private fun reset() {
        if (error) {
            indicateError()
            return
        }

        flSuffix.background = ContextCompat.getDrawable(
            context,
            R.drawable.bg_custom_text_field_prefix_suffix_regular
        )
        flPrefix.background = ContextCompat.getDrawable(
            context,
            R.drawable.bg_custom_text_field_prefix_suffix_regular
        )
        etContent.setTextColor(ContextCompat.getColor(context, R.color.black_900))

        if (etContent.hasFocus()) {
            focus()
        } else {
            ivPrefix.colorFilter = null
            ivSuffix.colorFilter = null
            tfRoot.background =
                ContextCompat.getDrawable(context, R.drawable.bg_custom_text_field_regular)
            tvPrefix.setTextColor(ContextCompat.getColor(context, R.color.black_500))
        }

        if (helper != null) {
            tvHelper.visibility = View.VISIBLE
            tvHelper.text = helper
            tvHelper.setTextColor(ContextCompat.getColor(context, R.color.black_500))
        } else {
            tvHelper.visibility = View.GONE
        }
    }

    private fun disable() {
        tfRoot.background =
            ContextCompat.getDrawable(context, R.drawable.bg_custom_text_field_disabled)
        flSuffix.background = ContextCompat.getDrawable(
            context,
            R.drawable.bg_custom_text_field_prefix_suffix_disabled
        )
        flPrefix.background = ContextCompat.getDrawable(
            context,
            R.drawable.bg_custom_text_field_prefix_suffix_disabled
        )
        etContent.setTextColor(ContextCompat.getColor(context, R.color.black_500))
    }

    private lateinit var tfRoot: ConstraintLayout
    private lateinit var flPrefix: FrameLayout
    private lateinit var tvPrefix: TextView
    private lateinit var tvSuffix: TextView
    private lateinit var tvLabel: TextView
    private lateinit var tvOptional: TextView
    private lateinit var tvHelper: TextView
    private lateinit var ivPrefix: ImageView
    private lateinit var flSuffix: FrameLayout
    private lateinit var ivSuffix: ImageView
    private lateinit var ivAction: ImageView
    private lateinit var etContent: EditText

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_text_field, this, true)
        initView()
        initAttributes(context, attrs)
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextField,
            0,
            0
        ).apply {
            try {
                prefixText = getString(R.styleable.CustomTextField_prefixText)
                prefixIcon = getDrawable(R.styleable.CustomTextField_prefixIcon)
                suffixText = getString(R.styleable.CustomTextField_suffixText)
                suffixIcon = getDrawable(R.styleable.CustomTextField_suffixIcon)
                shouldBeEnabled = getBoolean(R.styleable.CustomTextField_android_enabled, true)
                hint = getString(R.styleable.CustomTextField_android_hint)
                inputType = getInteger(R.styleable.CustomTextField_android_inputType, 1)
                enableClear = getBoolean(R.styleable.CustomTextField_enableClear, false)

                val defaultTextColor = context.getColor(R.color.black_500)
                label = getString(R.styleable.CustomTextField_label)
                optional = getBoolean(R.styleable.CustomTextField_optional, false)
                helper = getString(R.styleable.CustomTextField_helper)
                labelColor = getColor(R.styleable.CustomTextField_labelColor, defaultTextColor)
                optionalColor = getColor(R.styleable.CustomTextField_optionalColor, defaultTextColor)
                helperColor = getColor(R.styleable.CustomTextField_helperColor, defaultTextColor)
                minLine = getInt(R.styleable.CustomTextField_android_minLines, 1)
                maxLine = getInt(R.styleable.CustomTextField_android_maxLines, 1)
                maxLength = getInt(R.styleable.CustomTextField_android_maxLength, -1)
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        tfRoot = findViewById(R.id.tfRoot)
        flPrefix = findViewById(R.id.flPrefix)
        tvPrefix = findViewById(R.id.tvPrefixText)
        ivPrefix = findViewById(R.id.ivPrefixIcon)
        tvSuffix = findViewById(R.id.tvSuffixText)
        flSuffix = findViewById(R.id.flSuffix)
        ivSuffix = findViewById(R.id.ivSuffixIcon)
        etContent = findViewById(R.id.etTextField)
        tvLabel = findViewById(R.id.ctvLabel)
        tvHelper = findViewById(R.id.ctvHelper)
        tvOptional = findViewById(R.id.ctvLabelOptional)
        ivAction = findViewById(R.id.ivAction)

        etContent.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) focus()
            else reset()
        }

        tfRoot.setOnClickListener { etContent.requestFocus() }
        ivAction.setOnClickListener { etContent.setText("") }
    }

    private fun focus() {
        tfRoot.background =
            ContextCompat.getDrawable(context, R.drawable.bg_custom_text_field_focused)
        tvPrefix.setTextColor(ContextCompat.getColor(context, R.color.turqoise))
        etContent.setTextColor(ContextCompat.getColor(context, R.color.black_900))
        ivPrefix.setColorFilter(
            ContextCompat.getColor(context, R.color.turqoise),
            PorterDuff.Mode.SRC_IN
        )
        ivSuffix.setColorFilter(
            ContextCompat.getColor(context, R.color.turqoise),
            PorterDuff.Mode.SRC_IN
        )
    }

    fun doOnTextChanged(callback: (text: CharSequence?, start: Int, count: Int, after: Int) -> Unit) {
        etContent.doOnTextChanged { text, start, count, after ->
            callback(text, start, count, after)
        }
    }
}