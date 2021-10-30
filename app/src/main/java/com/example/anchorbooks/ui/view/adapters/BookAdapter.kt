package com.example.anchorbooks.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anchorbooks.R
import com.example.anchorbooks.data.model.Book
import com.example.anchorbooks.databinding.CardLibroBinding
import com.squareup.picasso.Picasso


class BookAdapter(private val books:List<Book>, private val context:Context): RecyclerView.Adapter<BookAdapter.ViewHolder>(), View.OnClickListener
{
    private lateinit var listener: View.OnClickListener

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val binding = CardLibroBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.card_libro, parent, false)
        card.setOnClickListener(this)
        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.binding.itemTitle.text = this.books.get(position).title
        holder.binding.itemAuthor.text = this.books.get(position).author
        Picasso.get().load(this.books.get(position).imageLink).into(holder.binding.itemImage)
        //Picasso.with(context).load(this.books[position].imageLink).placeholder(R.drawable.placeholder).error(R.drawable.error).fit().centerInside().noFade().into(viewHolder.itemImage);
        //Picasso.with(context).load(this.books.get(position).imageLink).into(holder.binding.itemImage)
    }

    override fun getItemCount(): Int
    {
        return books.size
    }

    fun setOnClickListener(listener: View.OnClickListener)
    {
        this.listener = listener
    }

    override fun onClick(view: View)
    {
        if(listener!=null)
        {
            listener.onClick(view)
        }
    }
}

