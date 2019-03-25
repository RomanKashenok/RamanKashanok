package com.kashanok.classes.homework.hw7

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kashanok.classes.R
import com.kashanok.classes.R.layout.activity_hw7


class Hw7MainActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Hw7MainActivity::class.java)
        }

        var orientation: Int? = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_hw7)
        orientation = resources.configuration.orientation
    }

    override fun onResume() {
        super.onResume()
        var currentFragment: Fragment = StudentsDetailsFragment()
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            StudentFragmentsOrchestrator.isDualMode = true
            StudentFragmentsOrchestrator.currentFragment?.let {
                currentFragment = it
            }
            setDefaultFragment(currentFragment)
        } else {
            StudentFragmentsOrchestrator.isDualMode = false
        }
        setListFragment()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        orientation = newConfig.orientation
        StudentFragmentsOrchestrator.isDualMode = orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private fun setListFragment() {
        val listFragment = StudentsListFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.studentsListFragmentFrame, listFragment)
        fragmentTransaction.commit()
    }

    private fun setDefaultFragment(defaultFragment: Fragment) {
        this.replaceFragment(defaultFragment)
    }

    fun replaceFragment(destFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.studentsRightPartFragmentFrame, destFragment)
        fragmentTransaction.commit()
    }
}
