package com.kashanok.classes.cw2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kashanok.classes.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn.setOnClickListener {
            startActivity(Cw2Activity.getIntent(this))
        }
    }
}
