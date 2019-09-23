package com.example.daggersample.ui.main.posts

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggersample.R
import com.example.daggersample.networking.Post
import kotlinx.android.synthetic.main.view_post.view.*

import org.jetbrains.anko.startActivity
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

import java.util.*
import kotlin.collections.ArrayList


class PostAdapter(var list: List<Post>?=null) : androidx.recyclerview.widget.RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    interface ClickListener {
        fun onClick(item: Post?)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list?.get(position))
    }


    override fun getItemCount(): Int {
        return if(list ==null) 0 else list!!.size
    }

    fun updateList(mlist:List<Post>?){
        list = mlist;
        notifyDataSetChanged()

    }



    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Post?) {
            itemView.title.text = item?.title
        }
    }




}