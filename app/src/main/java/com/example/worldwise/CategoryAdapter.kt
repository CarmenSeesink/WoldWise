package com.example.worldwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(
    private val categoryList: List<CategoryItem>,
    var mClickListener: OnCategoryItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentItem = categoryList[position]
//        holder.textView.text = currentItem.text
        holder.initialiser(categoryList.get(position), mClickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.category_text

        fun initialiser(categoryItem: CategoryItem, action: OnCategoryItemClickListener) {
            textView.text = categoryItem.text
            textView.setOnClickListener { view ->
                action.onCategoryClick(categoryItem, adapterPosition, view)
            }
        }
    }

}

interface OnCategoryItemClickListener {
    fun onCategoryClick(category: CategoryItem, position: Int, view: View)
}