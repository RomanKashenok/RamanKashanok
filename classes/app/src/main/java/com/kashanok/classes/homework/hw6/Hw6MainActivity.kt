package com.kashanok.classes.homework.hw6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.kashanok.classes.R.layout.activity_hw6
import com.kashanok.classes.common.ItemOffsetDecoration
import com.kashanok.classes.homework.hw6.recycler.Hw6RvAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hw6.*
import java.util.concurrent.TimeUnit

class Hw6MainActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, Hw6MainActivity::class.java)
        }
    }

    private var rvAdapter: Hw6RvAdapter? = null
    private var presenter: StudentsDataPresenter? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_hw6)

        rvStudents.layoutManager = LinearLayoutManager(this)

        rvAdapter = Hw6RvAdapter(this)
        rvStudents.adapter = rvAdapter
        rvStudents.setHasFixedSize(true)
        rvStudents.addItemDecoration(getItemDecoration())

        presenter = StudentsDataPresenter(rvAdapter)
        presenter?.execute()

        addButton.setOnClickListener {
            startActivity(Intent(this, Hw6EditActivity::class.java))
        }

        disposable = createEditTextFieldObservable()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("AAA", "ON SUCCESS: value: $it")
                val filteredItems =
                    StudentsDataPresenter.students.filter { student ->
                        student.model.name.toLowerCase().contains(it.toLowerCase())
                    }
                rvAdapter?.setNewItems(filteredItems)
            }, {
                Log.e("BBB", "ON ERROR: ${it.localizedMessage}")
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun getItemDecoration(): ItemOffsetDecoration {
        return ItemOffsetDecoration(
            this,
            com.kashanok.classes.R.dimen.dimen_27dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_9dp,
            com.kashanok.classes.R.dimen.dimen_0dp,
            com.kashanok.classes.R.dimen.dimen_27dp
        )
    }

    private fun createEditTextFieldObservable(): Observable<String> {
        return Observable.create<String> { subscriber ->

            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!subscriber.isDisposed) {
                        subscriber.onNext(s.toString())
                    }
                }
            }
            subscriber.setCancellable { searchStudentEditText.removeTextChangedListener(watcher) }
            searchStudentEditText.addTextChangedListener(watcher)
        }
    }
}
