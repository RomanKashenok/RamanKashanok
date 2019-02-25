package com.kashanok.classes.cw2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_to_go_to.*

class ToGoToActivity : AppCompatActivity() {

    companion object {
        const val BUTTON_NAME_EXTRA = "BUTTON_NAME_EXTRA"

        fun getIntent(context: Context, value: String): Intent {
            val intent = Intent(context, ToGoToActivity::class.java)
            intent.putExtra(BUTTON_NAME_EXTRA, value)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_go_to)

        val buttonText = intent.getStringExtra(BUTTON_NAME_EXTRA)

        val toast = Toast.makeText(this, getString(R.string.success_action), Toast.LENGTH_SHORT)
        toast.show()

        backButton.text = buttonText

        backButton.setOnClickListener{
            this.finish()
        }

        imageButton.setOnClickListener {
            startActivity(Intent(this, ShowIconActivity::class.java))
        }
    }
}
