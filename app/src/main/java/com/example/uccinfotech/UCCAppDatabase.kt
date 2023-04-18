package com.example.uccinfotech

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class UCCAppDatabase {

    var databaseName = "UccAppDataStore"
    var coursesTable = "Courses"

    var courseID = "COURSE_ID"
    var code = "COURSE_CODE"
    var courseName = "COURSE_NAME"
    var credits = "CREDITS"
    var pre_requisites = "PRE_REQUISITES"
    var description = "DESCRIPTION"

    var dbVersion = 1

    val sqlCreateTable =
        "CREATE TABLE IF NOT EXISTS $coursesTable (" +
                "$courseID INTEGER PRIMARY KEY," +
                "$code TEXT, " +
                "$courseName TEXT, " +
                "$credits TEXT, " +
                "$pre_requisites TEXT, " +
                "$description TEXT)"

    private var sqlDataB: SQLiteDatabase? = null

    constructor(context: Context): this(){
        val dataBase = UccAppDataHelper(context)
        sqlDataB = dataBase.writableDatabase
    }

    constructor()

    inner class UccAppDataHelper(context: Context):
        SQLiteOpenHelper(context, databaseName, null, dbVersion){

        private var context: Context? = context

        override fun onCreate(dataBase: SQLiteDatabase?) {
            dataBase!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context, "Database implemented...", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(dataBase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            dataBase!!.execSQL("Drop table if Exists $coursesTable")
        }
    }

    fun queryCoursesData(
        projection: Array<String>,
        selection: String,
        selectionArgs: Array<String>,
        sorOrder: String
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = coursesTable
        return queryBuilder.query(sqlDataB, projection, selection, selectionArgs, null, null, sorOrder)
    }

}