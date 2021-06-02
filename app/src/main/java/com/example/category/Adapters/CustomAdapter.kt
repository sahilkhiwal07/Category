package com.example.category.Adapters

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.category.Detailed
import com.example.category.Model.CustomItem
import com.example.category.R

class CustomAdapter(
    private val context: Context,
    private val onFavClicked: (CustomItem) -> Unit
) : ListAdapter<CustomItem, CustomAdapter.CustomHolder>(DIFF_ITEM_CALLBACK) {

    companion object {

        const val ITEM_KEY = "ITEM_KEY"

        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<CustomItem>() {
            override fun areItemsTheSame(oldItem: CustomItem, newItem: CustomItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CustomItem, newItem: CustomItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        getItem(position)?.let { items ->
            holder.title.text = items.title
            holder.names.text = items.founder_name
            holder.categories.text = items.category.toString()
            holder.upVotes.text = items.upvotes.toString()

            Glide.with(context)
                .load(items.image)
                .into(holder.images)

            holder.bookMark.setOnClickListener {
                onFavClicked.invoke(items)
            }

            if (items.isSelected) holder.bookMark.setImageResource(R.drawable.ic_bookmark_added)
            else holder.bookMark.setImageResource(R.drawable.ic_bookmark)


            holder.singleItem.setOnClickListener {
                val intent = Intent(context, Detailed::class.java)
                intent.putExtra(ITEM_KEY, items)
                context.startActivity(intent)
            }


        }

    }


    class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.text_title)
        val names: TextView = itemView.findViewById(R.id.text_name)
        val categories: TextView = itemView.findViewById(R.id.text_Category)
        val upVotes: TextView = itemView.findViewById(R.id.text_upvotes)
        val images: ImageView = itemView.findViewById(R.id.image)
        val bookMark: ImageView = itemView.findViewById(R.id.bookmark)
        val singleItem: CardView = itemView.findViewById(R.id.singleItems)

    }


}