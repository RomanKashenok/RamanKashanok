package com.kashanok.classes.homework.hw6.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.ImageLoader
import com.kashanok.classes.homework.hw6.Student
import com.kashanok.classes.homework.hw6.StudentsDataPresenter
import com.kashanok.classes.homework.hw6.StudentsDataPresenter.Companion.RANDOM_PICTURE_REPOSITORY_URL
import kotlinx.android.synthetic.main.student_card.view.*

class Hw6RvItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(student: BaseAdapterItem<Student>, position: Int) {
        view.nameTextView.text = student.model.name
        view.surnameTextView.text = student.model.surname
        view.ageNumber.text = student.model.age.toString()
        view.isDegreeNumber.text = student.model.isDegree.toString()
        ImageLoader.loadImage(view.studentImageView, (student as Hw6RvListItem).imageUrl ?: RANDOM_PICTURE_REPOSITORY_URL)

        setViewsTransitionNames()
    }

    private fun setViewsTransitionNames() {
        view.nameTextView.transitionName = StudentsDataPresenter.nameTransitionName
        view.surnameTextView.transitionName = StudentsDataPresenter.surnameTransitionName
        view.ageNumber.transitionName = StudentsDataPresenter.ageTransitionName
        view.studentImageView.transitionName = StudentsDataPresenter.pictureTransitionName
        view.isDegreeNumber.transitionName = StudentsDataPresenter.degreeTransitionName
    }
}