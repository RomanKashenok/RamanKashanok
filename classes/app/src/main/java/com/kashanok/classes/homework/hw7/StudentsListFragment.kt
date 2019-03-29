package com.kashanok.classes.homework.hw7

import android.content.Context
import android.content.res.Configuration
import android.opengl.Visibility
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.ItemOffsetDecoration
import com.kashanok.classes.homework.hw7.StudentsDetailsFragment.Companion.CURRENT_STUDENT_ID
import com.kashanok.classes.homework.hw7.recycler.Hw7RvAdapter
import kotlinx.android.synthetic.main.student_details_fragment.*
import kotlinx.android.synthetic.main.students_list_fragment.view.*

class StudentsListFragment : Fragment() {

    var rvAdapter: Hw7RvAdapter? = null
    private var presenter: StudentsDataPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.students_list_fragment, container, false)
        rvAdapter = Hw7RvAdapter(this)
        view.rvStudents.adapter = rvAdapter
        view.rvStudents.layoutManager = LinearLayoutManager(context)
        view.rvStudents.setHasFixedSize(true)
        context?.let {
            view.rvStudents.addItemDecoration(getItemDecoration(it))
        }

        presenter = StudentsDataPresenter(rvAdapter)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.execute()
    }

    private fun getItemDecoration(context: Context): ItemOffsetDecoration {
        return ItemOffsetDecoration(
            context,
            R.dimen.dimen_27dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_9dp,
            R.dimen.dimen_0dp,
            R.dimen.dimen_27dp
        )
    }
//
//
//    fun showStudentDetails(id: Int) {
//        val detailsFragment = StudentsDetailsFragment()
//        val bundle = Bundle()
//        bundle.putInt(CURRENT_STUDENT_ID, id)
//        detailsFragment.arguments = bundle
//
//        val fragmentToReplace = if (Hw7MainActivity.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            R.id.studentsRightPartFragmentFrame
//        } else {
//            R.id.studentsListFragmentFrame
//        }
//
//        StudentFragmentsOrchestrator.replaceFragment(this.activity?.supportFragmentManager, fragmentToReplace, detailsFragment)
//    }
}