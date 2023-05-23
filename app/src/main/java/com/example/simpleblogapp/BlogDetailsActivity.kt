package com.example.simpleblogapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BlogDetailsActivity : AppCompatActivity() {
    private lateinit var auteurTextView: TextView
    private lateinit var titreTextView: TextView
    private lateinit var textTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var backButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_details)

        backButton = findViewById(R.id.ic_back)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        auteurTextView = findViewById(R.id.auteurTextView)
        titreTextView = findViewById(R.id.titreTextView)
        textTextView = findViewById(R.id.textTextView2)
        dateTextView = findViewById(R.id.dateTextView)

        val extras = intent.extras
        if (extras != null) {
            val auteur = extras.getString("auteur")
            val titre = extras.getString("titre")
            val text = extras.getString("text")
            val date = extras.getString("date")

            auteurTextView.text = auteur
            titreTextView.text = titre
            textTextView.text = text
            dateTextView.text = date
        }
    }
}
