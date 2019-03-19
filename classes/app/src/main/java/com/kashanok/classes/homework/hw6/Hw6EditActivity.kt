package com.kashanok.classes.homework.hw6

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.ImageLoader
import com.kashanok.classes.homework.hw6.StudentsDataPresenter.Companion.students
import com.kashanok.classes.homework.hw6.recycler.Hw6RvListItem
import kotlinx.android.synthetic.main.activity_hw6_edit.*

class Hw6EditActivity : AppCompatActivity() {

    private lateinit var student: BaseAdapterItem<Student>
    private var isEditMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hw6_edit)

        isEditMode = intent.getBooleanExtra(Hw6DetailsActivity.EDIT_STUDENT_ACTION, false)

        if (isEditMode) {
            initializeWithStudent(intent)
        } else {
            ImageLoader.loadImage(studentImageView, StudentsDataPresenter.RANDOM_PICTURE_REPOSITORY_URL)
        }

        degreeSpinner.adapter =
            ArrayAdapter<Boolean>(this, android.R.layout.simple_spinner_dropdown_item, arrayListOf(true, false))

        saveButton.setOnClickListener {
            val newStudent = Student(
                getStudentId(),
                studentNameView.text.toString(),
                studentSurnameView.text.toString(),
                studentAgeView.text.toString().toInt(),
                degreeSpinner.selectedItem.toString().toBoolean()
            )
            if (isEditMode) {
                StudentsDataPresenter.editStudent(newStudent)
            } else {
                StudentsDataPresenter.addStudent(newStudent)
                student = Hw6RvListItem(newStudent)
            }
            onBackPressed()
        }
    }

    private fun initializeWithStudent(intent: Intent?) {
        student = students
            .filter { it.model.id == intent?.getIntExtra(StudentsDataPresenter.EXTRA_STUDENT_ID, -1) }[0]

        studentImageView.transitionName = StudentsDataPresenter.pictureTransitionName
        studentNameView.transitionName = StudentsDataPresenter.nameTransitionName
        studentSurnameView.transitionName = StudentsDataPresenter.surnameTransitionName
        studentAgeView.transitionName = StudentsDataPresenter.ageTransitionName
        ImageLoader.loadImage(
            studentImageView,
            (student as Hw6RvListItem).imageUrl ?: StudentsDataPresenter.RANDOM_PICTURE_REPOSITORY_URL
        )
        studentNameView.setText(student.model.name)
        studentSurnameView.setText(student.model.surname)
        studentAgeView.setText(student.model.age.toString())
    }

    private fun getStudentId(): Int {
        return if (isEditMode) student.model.id else getNewStudentId()
    }

    private fun getNewStudentId(): Int {
        var maxId = 0
        students.forEach {
            if (it.model.id > maxId) maxId = it.model.id
        }
        return ++maxId
    }

    override fun onBackPressed() {
        intent = Intent(this, Hw6DetailsActivity::class.java)
        intent.putExtra(StudentsDataPresenter.EXTRA_STUDENT_ID, student.model.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
