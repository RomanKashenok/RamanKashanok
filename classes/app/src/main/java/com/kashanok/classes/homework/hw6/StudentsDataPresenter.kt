package com.kashanok.classes.homework.hw6

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw6.recycler.Hw6RvAdapter
import com.kashanok.classes.homework.hw6.recycler.Hw6RvListItem
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

class StudentsDataPresenter(private val adapter: Hw6RvAdapter?) :
    AsyncTask<Unit, Unit, List<BaseAdapterItem<Student>>>() {

    companion object {
        private const val ASSETS_FILE_NAME = "students.json"
        private const val DATA_URI = "http://kiparo.ru/t/test.json"
        private const val PICTURE_URL = "https://picsum.photos/300/300/?random"

        const val RANDOM_PICTURE_REPOSITORY_URL = "https://picsum.photos/300/500/?random"

        const val EXTRA_STUDENT_ID = "com.kashanok.classes.homework.hw6.StudentsDataPresenter.EXTRA_STUDENT_ID"

        const val pictureTransitionName = "picture"
        const val nameTransitionName = "name"
        const val surnameTransitionName = "surname"
        const val ageTransitionName = "age"
        const val degreeTransitionName = "degree"

        var students = arrayListOf<BaseAdapterItem<Student>>()

        fun addStudent(student: Student) {
            students.add(Hw6RvListItem(student))
        }

        fun editStudent(student: Student) {
            var indexToEddit = -1
            val iterator = students.iterator()
            for ((index, value) in iterator.withIndex()) {
                if (value.model.id == student.id) indexToEddit = index
            }
            students[indexToEddit] = Hw6RvListItem(student)
        }

        fun deleteStudent(id: Int): Boolean {
            val iterator = students.iterator()
            for ((index, value) in iterator.withIndex()) {
                if (value.model.id == id) {
                    students.removeAt(index)
                    return true
                }
            }
            return false
        }
    }

    override fun doInBackground(vararg params: Unit?): List<BaseAdapterItem<Student>>? {
        return when {
            students.isEmpty() -> getStudentsFromUrl()
            else -> students
        }
    }

    override fun onPostExecute(result: List<BaseAdapterItem<Student>>?) {
        students = result as ArrayList<BaseAdapterItem<Student>>
        adapter?.setNewItems(students)
    }

    private fun getStudentsFromUrl(): List<BaseAdapterItem<Student>> {
        return parseStudents(getDataFromUrl(DATA_URI))
    }

    fun getStudentsFromAssets(context: Context): List<BaseAdapterItem<Student>> {
        return parseStudents(getDataFromAssets(context, ASSETS_FILE_NAME))
    }

    private fun parseStudents(json: String): List<BaseAdapterItem<Student>> {
        val response = Gson().fromJson(json, Response::class.java)
        val studentsList: ArrayList<BaseAdapterItem<Student>> = arrayListOf()
        response.people.forEach {
            val item = Hw6RvListItem(it)
            item.imageUrl = PICTURE_URL
            studentsList.add(item)
        }
        return studentsList
    }

    private fun getDataFromUrl(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    private fun getDataFromAssets(context: Context, fileName: String): String {
        val inStr = context.assets.open(fileName)
        val buffer = ByteArray(inStr.available())
        inStr.read(buffer)
        inStr.close()
        return String(buffer)
    }
}