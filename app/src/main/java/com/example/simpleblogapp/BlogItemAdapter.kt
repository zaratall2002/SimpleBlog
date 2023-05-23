package com.example.simpleblogapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BlogItemAdapter(private val context: Context, private val listBlogItem: List<BlogItem>) :
    BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listBlogItem.size
    }

    override fun getItem(position: Int): BlogItem {
        return listBlogItem[position]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.blog_item, null)
            val convertView1: View = inflater.inflate(R.layout.activity_blog_details, null)
            viewHolder = ViewHolder()
            viewHolder.auteurTextView = convertView1.findViewById<TextView>(R.id.auteurTextView)
            viewHolder.titreTextView = convertView.findViewById<TextView>(R.id.titre_blog_txt)
            viewHolder.textTextView = convertView.findViewById<TextView>(R.id.text_blog_txt)
            viewHolder.dateTextView = convertView1.findViewById<TextView>(R.id.dateTextView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        val blogItem = listBlogItem[position]
        viewHolder.auteurTextView!!.text = blogItem.auteur
        viewHolder.titreTextView!!.text = blogItem.titre
        viewHolder.textTextView!!.text = blogItem.text
        viewHolder.dateTextView!!.text = blogItem.date
        convertView.setOnClickListener { v: View? ->
            val intent = Intent(context, BlogDetailsActivity::class.java)
            intent.putExtra("auteur", blogItem.auteur)
            intent.putExtra("titre", blogItem.titre)
            intent.putExtra("text", blogItem.text)
            intent.putExtra("date", blogItem.date)
            context.startActivity(intent)
        }
        return convertView
    }

    internal class ViewHolder {
        var auteurTextView: TextView? = null
        var titreTextView: TextView? = null
        var textTextView: TextView? = null
        var dateTextView: TextView? = null
    }
}