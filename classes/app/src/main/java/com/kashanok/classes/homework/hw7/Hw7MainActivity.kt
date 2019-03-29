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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_hw7)

        var currentFragment: Fragment = StudentsDetailsFragment()
        StudentFragmentsOrchestrator.isDualMode = resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT
        if (StudentFragmentsOrchestrator.isDualMode) {
            StudentFragmentsOrchestrator.currentFragment?.let {
                currentFragment = it
            }
            setDefaultFragment(currentFragment)
        }
        setListFragment()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        StudentFragmentsOrchestrator.isDualMode = newConfig.orientation != Configuration.ORIENTATION_PORTRAIT
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
