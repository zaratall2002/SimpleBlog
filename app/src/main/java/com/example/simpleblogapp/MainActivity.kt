package com.example.simpleblogapp

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleblogapp.AddActivity
import com.example.simpleblogapp.BlogItem
import com.example.simpleblogapp.BlogItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var add_button: FloatingActionButton? = null
    var myDB: MyDatabase? = null
    var listBlogItem: List<BlogItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_button = findViewById<FloatingActionButton>(R.id.add_button)
        listView = findViewById<ListView>(R.id.listView)
        myDB = MyDatabase(this@MainActivity)
        add_button.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        })
        listBlogItem = saveDataInArrays()
        val adapter = BlogItemAdapter(this@MainActivity, listBlogItem!!)
        listView.setAdapter(adapter)
    }

    private fun saveDataInArrays(): List<BlogItem> {
        val listBlogItem: MutableList<BlogItem> = ArrayList()
        val cursor: Cursor = myDB.readAllData()
        if (cursor.count == 0) {
            Toast.makeText(this, "Pas de Blogs !!", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") val auteur =
                    cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_AUTHOR))
                @SuppressLint("Range") val title =
                    cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_TITLE))
                @SuppressLint("Range") val text =
                    cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_TEXT))
                @SuppressLint("Range") val date =
                    cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_DATE))
                listBlogItem.add(BlogItem(auteur, title, text, date))
            }
        }
        cursor.close()
        return listBlogItem
    }
}