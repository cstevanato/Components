package br.com.stv.edittext

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import kotlin.math.pow


class EditTextNumber :
        AppCompatEditText {

//    private val GROUPING_SEPARATOR = DecimalFormatSymbols.getInstance().getGroupingSeparator()
//    private val DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance().getDecimalSeparator()

    var ignore: Boolean = false
    var old = ""

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        val attributes = context!!.obtainStyledAttributes(attrs, R.styleable.EditTextNumber)
        val countDigits = attributes.getInt(R.styleable.EditTextNumber_countDigit, 0)
        val countPrecision = attributes.getInt(R.styleable.EditTextNumber_countPrecision, 0)

        val value = editValue(this.text.toString(), countPrecision, countDigits)

        this.gravity = Gravity.RIGHT
        this.inputType = InputType.TYPE_CLASS_NUMBER or
                InputType.TYPE_NUMBER_FLAG_DECIMAL or
                InputType.TYPE_NUMBER_FLAG_SIGNED
        this.setSingleLine()
        //this.text.replace(0, this.text.length, value, 0, value.length)
        //this.setSelection(this.text.length)

        this.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                var value = s.toString()

                if (ignore || old.equals(value)) {
                    ignore = false
                    return
                }
                ignore = true
                value = editValue(value, countPrecision, countDigits)
                old = value
                setText(value) // ?.replace(0, s.length, value, 0, value.length)
                setSelection(value.length)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun editValue(value: String, countPrecision: Int, countDigits: Int): String {
        var ret = value.replace("[^\\d]".toRegex(), "")

        if (ret.isEmpty() || ret.toFloat().equals(0f)) {
            ret = if (countPrecision > 0) "0,00" else "0"
        } else if (ret.length > countDigits) {
            ret =  formatValue(countPrecision, ret.substring(0, ret.length -1))
        } else if (countPrecision > 0) {
            ret = formatValue(countPrecision, ret)
        } else  {
            ret = ret.toBigDecimal().toString()
        }
        return ret
    }

    private fun formatValue(countPrecision: Int, ret: String) =

            String.format("%." + countPrecision + "f",
                    ret.toBigDecimal().divide((10f.pow(countPrecision)).toBigDecimal()))

}