package com.example.worldwise

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(
    private val categoryList: List<CategoryItem>,
    var mClickListener: OnCategoryItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView, parent)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentItem = categoryList[position]
//        holder.textView.text = currentItem.text
        holder.initialiser(categoryList.get(position), mClickListener)
    }

    inner class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.category_text
        val imageView: ImageView = itemView.category_image
        fun initialiser(categoryItem: CategoryItem, action: OnCategoryItemClickListener) {
            textView.text = categoryItem.text
            Glide.with(itemView).load(Uri.parse("file:///android_asset/${categoryItem.image}")).into(imageView)
            textView.setOnClickListener { view ->
                action.onCategoryClick(categoryItem, adapterPosition, view)
            }
        }
    }
}

interface OnCategoryItemClickListener {
    fun onCategoryClick(category: CategoryItem, position: Int, view: View)
}