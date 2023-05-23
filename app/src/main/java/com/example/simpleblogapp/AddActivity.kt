package com.example.simpleblogapp
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

class AddActivity : AppCompatActivity() {
    private lateinit var auteurInput: EditText
    private lateinit var titreInput: EditText
    private lateinit var contenueInput: EditText
    private lateinit var addBlogButton: EditText
    private lateinit var closeButton: FloatingActionButton

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        auteurInput = findViewById<EditText>(R.id.auteur_input)
        titreInput = findViewById<EditText>(R.id.titre_input)
        contenueInput = findViewById<EditText>(R.id.contenue_input)
        addBlogButton = findViewById<EditText>(R.id.add_blog_button)
        closeButton = findViewById(R.id.close_button)

        val dateBlog = LocalDate.now()
        val dateString = dateBlog.toString()

        addBlogButton.setOnClickListener {
            val myDB = MyDatabase(this)
            myDB.addBlog(
                auteurInput.text.toString().trim(),
                titreInput.text.toString().trim(),
                contenueInput.text.toString().trim(),
                dateString
            )
        }

        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
