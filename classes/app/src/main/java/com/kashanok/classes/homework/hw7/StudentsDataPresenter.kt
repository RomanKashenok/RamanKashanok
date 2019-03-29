package com.kashanok.classes.homework.hw7

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import com.kashanok.classes.BuildConfig
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw7.recycler.Hw7RvAdapter
import com.kashanok.classes.homework.hw7.recycler.Hw7RvListItem
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import java.util.logging.Level

class StudentsDataPresenter(private val adapter: Hw7RvAdapter?) :
    AsyncTask<Unit, Unit, List<BaseAdapterItem<Student>>>() {

    companion object {
        private const val ASSETS_FILE_NAME = "students.json"
        private const val DATA_URI = "http://kiparo.ru/t/test.json"
        private const val PICTURE_URL = "https://picsum.photos/300/300/?random"

        const val RANDOM_PICTURE_REPOSITORY_URL = "https://picsum.photos/300/500/?random"

        var students = arrayListOf<BaseAdapterItem<Student>>()

        fun addStudent(student: Student) {
            students.add(Hw7RvListItem(student))
        }

        fun editStudent(student: Student) {
            var indexToEddit = -1
            val iterator = students.iterator()
            for ((index, value) in iterator.withIndex()) {
                if (value.model.id == student.id) indexToEddit = index
            }
            students[indexToEddit] = Hw7RvListItem(student)
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
            val item = Hw7RvListItem(it)
            item.imageUrl = PICTURE_URL
            studentsList.add(item)
        }
        return studentsList
    }

    private fun getDataFromUrl(url: String): String {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        clientBuilder.connectTimeout(5, TimeUnit.SECONDS)
        val client: OkHttpClient = clientBuilder.build()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body()?.string() ?: ""
    }

    private fun getDataFromAssets(context: Context, fileName: String): String {
        val inStr = context.assets.open(fileName)
        val buffer = ByteArray(inStr.available())
        inStr.read(buffer)
        inStr.close()
        return String(buffer)
    }
}