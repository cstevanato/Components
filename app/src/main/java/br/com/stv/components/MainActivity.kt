package br.com.stv.components

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list_of_itens = arrayOf("CircleTextView", "EditTextNumber")

        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_itens)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp_test.setAdapter(array_adapter)


        button.setOnClickListener {
            val intent = when (sp_test.selectedItem) {
                "CircleTextView" -> Intent(this, TestCircleTextViewActivity::class.java)
                else -> null
            }

            intent?.let {
                startActivity(intent)
            }
        }
    }
}
