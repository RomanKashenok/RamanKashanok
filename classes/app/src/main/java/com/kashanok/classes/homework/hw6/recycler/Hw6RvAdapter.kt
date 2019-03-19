package com.kashanok.classes.homework.hw6.recycler

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw6.Student
import com.kashanok.classes.homework.hw6.Hw6DetailsActivity
import com.kashanok.classes.homework.hw6.StudentsDataPresenter
import kotlinx.android.synthetic.main.student_card.view.*

class Hw6RvAdapter(private val activity: Activity) : RecyclerView.Adapter<Hw6RvItemHolder>() {

    companion object {
        var students: List<BaseAdapterItem<Student>> = arrayListOf()
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hw6RvItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(com.kashanok.classes.R.layout.student_card, parent, false)
        val holder = Hw6RvItemHolder(view)

        holder.itemView.setOnClickListener {

            val sharedIntent = Intent(parent.context, Hw6DetailsActivity::class.java)
            sharedIntent.putExtra(StudentsDataPresenter.EXTRA_STUDENT_ID, students[holder.adapterPosition].model.id)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(holder.itemView.studentImageView, StudentsDataPresenter.pictureTransitionName),
                Pair(holder.itemView.nameTextView, StudentsDataPresenter.nameTransitionName),
                Pair(holder.itemView.surnameTextView, StudentsDataPresenter.surnameTransitionName),
                Pair(holder.itemView.ageNumber, StudentsDataPresenter.ageTransitionName),
                Pair(holder.itemView.isDegreeNumber, StudentsDataPresenter.degreeTransitionName)
                )

            parent.context.startActivity(sharedIntent, options.toBundle())
        }

        return holder
    }

    override fun onBindViewHolder(holder: Hw6RvItemHolder, position: Int) {
        holder.bind(students[position], position)
    }

    fun setNewItems(items: List<BaseAdapterItem<Student>>) {
        students = items
        notifyDataSetChanged()
    }
}