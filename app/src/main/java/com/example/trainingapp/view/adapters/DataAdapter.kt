package com.example.trainingapp.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.interfaces.AdapterItemClickListener
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.omdb_list_item.view.*

class DataAdapter(dataList: List<DataModel>, clickListener: AdapterItemClickListener? = null): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var data: MutableList<DataModel> = ArrayList()
    private var listener: AdapterItemClickListener? = null

    init {
        data = dataList.toMutableList()
        listener = clickListener
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: DataModel, listener: AdapterItemClickListener?) {
            itemView.tvTitleContent.text = item.title
            itemView.tvRatedContent.text = item.rated
            itemView.tvYearContent.text = item.year
            try {
                Picasso.get()
                    .load(item.poster)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_STORE)
                    .into(itemView.ivPoster)
            } catch (e: Exception) {
                Log.e(DataAdapter::class.java.simpleName, "Load Poster Exception: $e")
            }

            listener?.apply {
                itemView.setOnClickListener {
                    item.imdbID?.let { id ->
                        onItemClickListener(id)
                    }
                }
            }
        }
    }

    fun setData(newData: List<DataModel>) {
        data = newData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.omdb_list_item, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].apply {
            holder.bind(this, listener)
        }
    }

}