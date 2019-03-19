package com.kashanok.classes.homework.hw6

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.ImageLoader
import com.kashanok.classes.homework.hw6.StudentsDataPresenter.Companion.RANDOM_PICTURE_REPOSITORY_URL
import com.kashanok.classes.homework.hw6.StudentsDataPresenter.Companion.students
import com.kashanok.classes.homework.hw6.recycler.Hw6RvListItem
import kotlinx.android.synthetic.main.activity_student_details.*

class Hw6DetailsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Hw6DetailsActivity::class.java)
        }

        const val EDIT_STUDENT_ACTION = "com.kashanok.classes.homework.hw6.Hw6DetailsActivity.EDIT_STUDENT_ACTION"
    }

    private lateinit var student: BaseAdapterItem<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        student = students
            .filter { it.model.id == intent.getIntExtra(StudentsDataPresenter.EXTRA_STUDENT_ID, -1) }[0]

        studentImageView.transitionName = StudentsDataPresenter.pictureTransitionName
        studentNameView.transitionName = StudentsDataPresenter.nameTransitionName
        studentSurnameView.transitionName = StudentsDataPresenter.surnameTransitionName
        studentAgeView.transitionName = StudentsDataPresenter.ageTransitionName
        studentDegreeValueView.transitionName = StudentsDataPresenter.degreeTransitionName

        ImageLoader.loadImage(
            studentImageView,
            (student as Hw6RvListItem).imageUrl ?: RANDOM_PICTURE_REPOSITORY_URL
        )
        studentNameView.text = student.model.name
        studentSurnameView.text = student.model.surname
        studentAgeView.text = student.model.age.toString()
        studentDegreeValueView.text = student.model.isDegree.toString()

        deleteButton.setOnClickListener {
            if (!StudentsDataPresenter.deleteStudent(student.model.id)) {
                Toast.makeText(this@Hw6DetailsActivity, R.string.alreadyRemoved, Toast.LENGTH_LONG)
            }
            onBackPressed()
        }

        editButton.setOnClickListener {

            val sharedIntent = Intent(this, Hw6EditActivity::class.java)
            sharedIntent.putExtra(StudentsDataPresenter.EXTRA_STUDENT_ID, student.model.id)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair(studentImageView, StudentsDataPresenter.pictureTransitionName),
                Pair(studentNameView, StudentsDataPresenter.nameTransitionName),
                Pair(studentSurnameView, StudentsDataPresenter.surnameTransitionName),
                Pair(studentAgeView, StudentsDataPresenter.ageTransitionName)
            )
            sharedIntent.putExtra(EDIT_STUDENT_ACTION, true)
            startActivity(sharedIntent, options.toBundle())
        }
    }

    override fun onResume() {
        super.onResume()
        if (StudentsDataPresenter.students.filter { it.model.id == student.model.id }.isNullOrEmpty()) {
            deleteButton.background.setColorFilter(resources.getColor(R.color.colorGrayLight), PorterDuff.Mode.MULTIPLY)
            deleteButton.isEnabled = false
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, Hw6MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
