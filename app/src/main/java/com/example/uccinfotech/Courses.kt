package com.example.uccinfotech

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.uccinfotech.databinding.ActivityCoursesBinding

class Courses : AppCompatActivity() {

    private var coursesList = ArrayList<CoursesDataClass>()
    private lateinit var sharedPref : SharedPreferences

    private lateinit var binding: ActivityCoursesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("My_Data", Context.MODE_PRIVATE)
        val sortingOptions = sharedPref.getString("Sort", "ascending")
        when (sortingOptions) {
            "ascending" -> loadQueryAscending("%")
        }

    }

    override fun onResume(){
        super.onResume()
        val dSorting = sharedPref.getString("Sort", "ascending")
        when(dSorting){
            "ascending" -> loadQueryAscending("%")
        }
    }

    @SuppressLint("Range")
    private fun loadQueryAscending(title: String){
        val uccAppDB = UCCAppDatabase(this)
        val projections = arrayOf("COURSE_ID", "COURSE_CODE", "COURSE_NAME", "CREDITS", "PRE_REQUISITES", "DESCRIPTION")
        val selectionArgs = arrayOf(title)
        val cursor = uccAppDB.queryCoursesData(projections, "ID like ?", selectionArgs, "ID")
        coursesList.clear()

        if(cursor.moveToFirst()){
            do{
                val ID = cursor.getInt(cursor.getColumnIndex("COURSE_ID"))
                val CODE = cursor.getString(cursor.getColumnIndex("COURSE_CODE"))
                val COURSE_NAME = cursor.getString(cursor.getColumnIndex("COURSE_NAME"))
                val CREDITS = cursor.getString(cursor.getColumnIndex("CREDITS"))
                val PRE_REQUISITES = cursor.getString(cursor.getColumnIndex("PRE_REQUISITES"))
                val DESCRIPTIO = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))

                coursesList.add(CoursesDataClass(ID, CODE, COURSE_NAME, CREDITS, PRE_REQUISITES, DESCRIPTIO))

            }while(cursor.moveToPrevious())

            val myCoursesAdapter = CoursesAdapter(this, coursesList)

            val courseViews: ListView = findViewById(R.id.courseView)
            courseViews.adapter = myCoursesAdapter
        }
    }

    inner class CoursesAdapter: BaseAdapter{
        private var listCourseAdapter = ArrayList<CoursesDataClass>()
        var context: Context? = null

        constructor(context: Context, listCourseAdapter: java.util.ArrayList<CoursesDataClass>) : super() {
            this.listCourseAdapter = listCourseAdapter
            this.context = context
        }

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.course_card_view, null)
            val myCourses = listCourseAdapter[position]

            val tvCourseCode: TextView = myView.findViewById(R.id.course_code)
            val tvCourseName: TextView = myView.findViewById(R.id.course_name)
            val tvCredits: TextView = myView.findViewById(R.id.credits)
            val tvPreRequisites: TextView = myView.findViewById(R.id.course_pre_requisites)
            val tvCourseDescription: TextView = myView.findViewById(R.id.course_description)

            tvCourseCode.text = myCourses.courseCode
            tvCourseName.text = myCourses.courseName
            tvCredits.text = myCourses.courseCredits
            tvPreRequisites.text = myCourses.preRequisites
            tvCourseDescription.text = myCourses.description

            return myView

        }
        override fun getCount(): Int {
            return listCourseAdapter.size
        }

        override fun getItem(position: Int): Any {
            return listCourseAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }


}