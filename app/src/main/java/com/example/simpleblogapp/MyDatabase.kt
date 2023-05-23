package com.example.simpleblogapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

internal class MyDatabase(private val context: Context?) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_TEXT + " TEXT," +
                COLUMN_DATE + " TEXT);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addBlog(auteur: String?, title: String?, text: String?, date: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_AUTHOR, auteur)
        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_TEXT, text)
        cv.put(COLUMN_DATE, date)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "L'ajout a échoué", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "L'ajout est un Succès", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM " + TABLE_NAME
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    companion object {
        private const val DATABASE_NAME = "simpleblogapp"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "mesblogs"
        const val COLUMN_ID = "_id"
        const val COLUMN_AUTHOR = "auteur_blog"
        const val COLUMN_TITLE = "titre_blog"
        const val COLUMN_TEXT = "text_blog"
        const val COLUMN_DATE = "date_blog"
    }
}