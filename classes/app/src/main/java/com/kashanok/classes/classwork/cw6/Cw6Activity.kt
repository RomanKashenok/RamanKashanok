package com.kashanok.classes.classwork.cw6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import kotlinx.android.synthetic.main.activity_cw6.*
import java.util.*
import kotlin.random.Random

class Cw6Activity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Cw6Activity::class.java)
        }

        private const val PREF_KEY = "PREF_KEY"
    }

    private var preferences: SharedPreferences? = null
    private lateinit var rvAdapter: Cw6RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw6)
        preferences = getSharedPreferences("aaa", Context.MODE_PRIVATE)

        recyclerView.layoutManager = LinearLayoutManager(this)
        rvAdapter = Cw6RecyclerViewAdapter()
        recyclerView.adapter = rvAdapter
        rvAdapter.setNewItems(generateRandomStudentData())
        recyclerView.setHasFixedSize(true) // Set view dimensions and saves them. Used for increasing rendering speed (if view dimensions stays unchanged)
    }

    private fun generateRandomStudentData(): List<BaseAdapterItem<Cw6StudentItem>> {
        val itemsList = arrayListOf<BaseAdapterItem<Cw6StudentItem>>()
        for (i in 0..15) {
            val name = StringBuilder()
            Random.nextBytes(200).forEach { name.append(it.toChar()) }
            itemsList.add(Cw6ListItem(Cw6StudentItem(name.toString(), "https://picsum.photos/150/150/?random")))
        }
        return itemsList
    }

    override fun onPause() {
        super.onPause()
        preferences?.edit()?.putLong(PREF_KEY, System.currentTimeMillis())?.apply()
    }

    override fun onResume() {
        super.onResume()
        val millis = preferences?.getLong(PREF_KEY, 0) ?: System.currentTimeMillis()
        Toast.makeText(
            this,
            "LAST TIME YOU WAS HERE IN : " + Date(millis),
            Toast.LENGTH_LONG
        ).show()
    }

}
