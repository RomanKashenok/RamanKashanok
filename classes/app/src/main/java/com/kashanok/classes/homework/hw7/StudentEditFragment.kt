package com.kashanok.classes.homework.hw7

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kashanok.classes.R
import com.kashanok.classes.common.ImageLoader
import com.kashanok.classes.homework.hw7.StudentFragmentsOrchestrator.isEditMode
import com.kashanok.classes.homework.hw7.recycler.Hw7RvListItem
import kotlinx.android.synthetic.main.student_edit_fragment.view.*

class StudentEditFragment : Fragment() {

    private var studentId: Int = -1
    private val fragmentId: Int = R.layout.student_edit_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            studentId = it.getInt(StudentsDetailsFragment.CURRENT_STUDENT_ID)
        }
        return inflater.inflate(fragmentId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student = StudentsDataPresenter.students.find { it.model.id == studentId }
        student?.let {
                ImageLoader.loadImage(
                    view.studentImageView,
                    (it as Hw7RvListItem).imageUrl ?: StudentsDataPresenter.RANDOM_PICTURE_REPOSITORY_URL
                )
                view.studentNameView.setText(it.model.name)
                view.studentSurnameView.setText(it.model.surname)
                view.studentAgeView.setText(it.model.age.toString())
            }

        view.degreeSpinner.adapter =
            ArrayAdapter<Boolean>(context, android.R.layout.simple_spinner_dropdown_item, arrayListOf(true, false))

        view.saveButton.setOnClickListener {
            val id  = getStudentId()
            studentId = id
            val newStudent = Student(
                id,
                view.studentNameView.text.toString(),
                view.studentSurnameView.text.toString(),
                view.studentAgeView.text.toString().toInt(),
                view.degreeSpinner.selectedItem.toString().toBoolean()
            )
            if (isEditMode) {
                StudentsDataPresenter.editStudent(newStudent)
            } else {
                StudentsDataPresenter.addStudent(newStudent)
            }
            studentId.let {
                if (activity?.supportFragmentManager?.backStackEntryCount ?: 0 > 0) {
                    StudentFragmentsOrchestrator.updateStudentsList(activity?.supportFragmentManager)
                    activity?.onBackPressed()
                } else {
                    StudentFragmentsOrchestrator.showStudentDetails(
                        id,
                        activity?.supportFragmentManager
                    )
                }
            }
            StudentFragmentsOrchestrator.updateStudentsList(activity?.supportFragmentManager)
        }
    }

    private fun getStudentId(): Int {
        return if (StudentFragmentsOrchestrator.isEditMode) {
            val filteredStudents = StudentsDataPresenter.students.filter { it.model.id == studentId }
            when {
                !filteredStudents.isNullOrEmpty() -> { filteredStudents[0].model.id }
                else -> {
                    StudentFragmentsOrchestrator.isEditMode = false
                    getNewStudentId()
                }
            }
        } else getNewStudentId()
    }

    private fun getNewStudentId(): Int {
        var maxId = 0
        StudentsDataPresenter.students.forEach {
            if (it.model.id > maxId) maxId = it.model.id
        }
        return ++maxId
    }

}