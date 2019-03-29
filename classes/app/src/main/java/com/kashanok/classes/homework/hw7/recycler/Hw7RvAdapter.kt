package com.kashanok.classes.homework.hw7.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw7.Student
import com.kashanok.classes.homework.hw7.StudentFragmentsOrchestrator
import com.kashanok.classes.homework.hw7.StudentsListFragment

class Hw7RvAdapter(val fragment: StudentsListFragment) : RecyclerView.Adapter<Hw7RvItemHolder>() {

    companion object {
        var students: List<BaseAdapterItem<Student>> = arrayListOf()
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hw7RvItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_card, parent, false)
        val holder = Hw7RvItemHolder(view)

        holder.itemView.setOnClickListener {
            StudentFragmentsOrchestrator.showStudentDetails(
                students[holder.adapterPosition].model.id,
                fragment.activity?.supportFragmentManager
            )
        }

        return holder
    }

    override fun onBindViewHolder(holder: Hw7RvItemHolder, position: Int) {
        holder.bind(students[position], position)
    }

    fun setNewItems(items: List<BaseAdapterItem<Student>>) {
        students = items
        notifyDataSetChanged()
    }
}