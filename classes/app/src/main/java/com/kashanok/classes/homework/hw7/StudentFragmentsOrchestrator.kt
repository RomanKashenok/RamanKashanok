package com.kashanok.classes.homework.hw7

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.kashanok.classes.R

object StudentFragmentsOrchestrator {
    var isDualMode: Boolean = false
    var currentFragment: Fragment? = null
    var isEditMode: Boolean = false

    fun replaceFragment(supportManager: FragmentManager?, idToReplace: Int, fragment: Fragment) {
        currentFragment = fragment
        val transaction = supportManager?.beginTransaction()
        transaction?.replace(idToReplace, fragment)
        transaction?.addToBackStack(fragment::class.java.toString())
        transaction?.commit()
    }

    fun showStudentDetails(id: Int, fragmentManager: FragmentManager?) {
        val detailsFragment = StudentsDetailsFragment()
        val bundle = Bundle()
        bundle.putInt(StudentsDetailsFragment.CURRENT_STUDENT_ID, id)
        detailsFragment.arguments = bundle

        val fragmentToReplace = if (isDualMode) {
            R.id.studentsRightPartFragmentFrame
        } else {
            R.id.studentsListFragmentFrame
        }
        replaceFragment(fragmentManager, fragmentToReplace, detailsFragment)
    }

    fun updateStudentsList(fragmentManager: FragmentManager?) {
        fragmentManager?.fragments?.forEach { fragment ->
            if(fragment::class.java == StudentsListFragment::class.java) {
                (fragment as StudentsListFragment).rvAdapter?.notifyDataSetChanged()
            }
        }
    }
}