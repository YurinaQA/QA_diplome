package ru.iteco.fmhandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.iteco.fmhandroid.databinding.ItemNewsControlPanelBinding
import ru.iteco.fmhandroid.entity.NewsEntity
import java.text.SimpleDateFormat
import ru.iteco.fmhandroid.R
import java.util.*

class NewsControlPanelAdapter(
    private var newsItems: List<NewsEntity>,
    private val categoryIconProvider: (Int) -> Int?, // Возвращает ресурс иконки для newsCategoryId
    private val listener: OnNewsItemClickListener
) : RecyclerView.Adapter<NewsControlPanelAdapter.NewsViewHolder>() {

    interface OnNewsItemClickListener {
        fun onNewsClicked(newsItem: NewsEntity, position: Int)
        fun onDeleteClicked(newsItem: NewsEntity)
        fun onEditClicked(newsItem: NewsEntity)
    }

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    inner class NewsViewHolder(private val binding: ItemNewsControlPanelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsEntity, position: Int) {
            binding.newsItemTitleTextView.text = newsItem.title
            binding.newsItemDescriptionTextView.text = newsItem.description
            binding.newsItemAuthorTextView.text = newsItem.creatorName

            binding.newsItemPublicationTextView.text = if (newsItem.publishDate > 0)
                dateFormat.format(Date(newsItem.publishDate)) else "-"
            binding.newsItemCreateDateTextView.text = if (newsItem.createDate > 0)
                dateFormat.format(Date(newsItem.createDate)) else "-"

            val iconRes = categoryIconProvider(newsItem.newsCategoryId)
            if (iconRes != null) {
                binding.categoryIconImageView.setImageResource(iconRes)
                binding.categoryIconImageView.visibility = View.VISIBLE
            } else {
                binding.categoryIconImageView.visibility = View.GONE
            }

            if (newsItem.publishEnabled) {
                binding.newsItemPublishedTextView.text = binding.root.context.getString(R.string.news_control_panel_active)
                binding.newsItemPublishedIconImageView.visibility = View.VISIBLE
            } else {
                binding.newsItemPublishedTextView.text = binding.root.context.getString(R.string.news_control_panel_not_active)
                binding.newsItemPublishedIconImageView.visibility = View.GONE
            }

            binding.newsItemDescriptionTextView.visibility = if (newsItem.isOpen) View.VISIBLE else View.GONE

            binding.viewNewsItemImageView.setImageResource(
                if (newsItem.isOpen) R.drawable.expand_less_24 else R.drawable.expand_more_24
            )

            // Клики по элементам
            binding.root.setOnClickListener {
                listener.onNewsClicked(newsItem, position)
            }
            binding.deleteNewsItemImageView.setOnClickListener {
                listener.onDeleteClicked(newsItem)
            }
            binding.editNewsItemImageView.setOnClickListener {
                listener.onEditClicked(newsItem)
            }
            binding.viewNewsItemImageView.setOnClickListener {
                listener.onNewsClicked(newsItem, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsControlPanelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsItems[position], position)
    }

    override fun getItemCount(): Int = newsItems.size

    fun updateItems(newItems: List<NewsEntity>) {
        newsItems = newItems
        notifyDataSetChanged()
    }

    // Вспомогательный метод для переключения isOpen и обновления конкретного элемента
    fun toggleOpenState(position: Int) {
        val item = newsItems[position]
        newsItems = newsItems.toMutableList().also {
            it[position] = item.copy(isOpen = !item.isOpen)
        }
        notifyItemChanged(position)
    }
}
