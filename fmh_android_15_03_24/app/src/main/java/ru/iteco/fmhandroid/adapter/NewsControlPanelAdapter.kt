package ru.iteco.fmhandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.iteco.fmhandroid.R

data class NewsItem(
    val id: Int,
    val title: String,
    val description: String
)

class NewsControlPanelAdapter(
    private var items: List<NewsItem>,
    private val listener: OnNewsItemClickListener
) : RecyclerView.Adapter<NewsControlPanelAdapter.NewsViewHolder>() {

    interface OnNewsItemClickListener {
        fun onNewsClicked(newsItem: NewsItem)
    }

    fun updateItems(newItems: List<NewsItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Убедись, что в ресурсах есть файл item_news_control_panel.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_control_panel, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = items[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int = items.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.news_item_title_text_view)
        private val descriptionText: TextView = itemView.findViewById(R.id.news_item_description_text_view)

        fun bind(newsItem: NewsItem) {
            titleText.text = newsItem.title
            descriptionText.text = newsItem.description

            itemView.setOnClickListener {
                listener.onNewsClicked(newsItem)
            }
        }
    }
}
