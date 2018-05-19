package br.com.stv.circle

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity


class CircleTextView(context: Context?,
                     attrs: AttributeSet?) :
        TextView(context, attrs) {

    init {
        val attributes = context!!.obtainStyledAttributes(attrs, R.styleable.CircleTextView)
        this.background = context!!.getDrawable(R.drawable.cicle_text_view)

        this.text = configStyleBoldText()
        this.gravity = Gravity.CENTER


        configBackgroundColor(attributes)

    }

    private fun configStyleBoldText(): SpannableString {
        val spanString = SpannableString(this.text)
        spanString.setSpan(StyleSpan(Typeface.BOLD), 0, spanString.length, 0)
        return spanString
    }

    private fun configBackgroundColor(attributes: TypedArray) {
        val colorString: String? = attributes.getString(R.styleable.CircleTextView_background_color)
        if (colorString != null) {
            val parseColor = Color.parseColor(colorString)
            val backgroundColor: GradientDrawable = this.background as GradientDrawable
            backgroundColor.setColor(parseColor)
        }
    }
}