package com.kashanok.classes.homework.hw7

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.ImageLoader
import com.kashanok.classes.homework.hw7.Hw7MainActivity.Companion.orientation
import com.kashanok.classes.homework.hw7.recycler.Hw7RvListItem
import kotlinx.android.synthetic.main.student_details_fragment.view.*

class StudentsDetailsFragment : Fragment() {

    companion object {
        const val CURRENT_STUDENT_ID = "CURRENT_STUDENT_ID"
    }

    private var studentId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        studentId = arguments?.getInt(CURRENT_STUDENT_ID)
        return inflater.inflate(R.layout.student_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.editButton.visibility = View.VISIBLE
        view.deleteButton.visibility = View.VISIBLE
        val students = StudentsDataPresenter.students.filter { it.model.id == studentId }
        if (!students.isNullOrEmpty()) {
            students[0].let {
                ImageLoader.loadImage(
                    view.studentImageView,
                    (it as Hw7RvListItem).imageUrl ?: StudentsDataPresenter.RANDOM_PICTURE_REPOSITORY_URL
                )
                view.studentNameView.text = it.model.name
                view.studentSurnameView.text = it.model.surname
                view.studentAgeView.text = it.model.age.toString()
                view.studentDegreeValueView.text = it.model.isDegree.toString()
            }
        }

        if (studentId == null) {
            view.editButton.visibility = View.GONE
            view.deleteButton.visibility = View.GONE
        }

        view.editButton.setOnClickListener {
            StudentFragmentsOrchestrator.isEditMode = true
            editCurrentStudent()
        }

        view.deleteButton.setOnClickListener {
            if (!StudentsDataPresenter.deleteStudent(StudentsDataPresenter.students.filter { it.model.id == studentId }[0].model.id)) {
                Toast.makeText(activity, R.string.alreadyRemoved, Toast.LENGTH_LONG)
            }
            StudentFragmentsOrchestrator.updateStudentsList(activity?.supportFragmentManager)
            activity?.onBackPressed()
        }
    }

    private fun editCurrentStudent() {
        val editFragment = StudentEditFragment()
        val bundle = Bundle()
        bundle.putInt(CURRENT_STUDENT_ID, studentId ?: -1)
        editFragment.arguments = bundle
        val fragmentToReplace = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            R.id.studentsRightPartFragmentFrame
        } else {
            R.id.studentsListFragmentFrame
        }
        StudentFragmentsOrchestrator.replaceFragment(
            activity?.supportFragmentManager,
            fragmentToReplace,
            editFragment
        )
    }
}