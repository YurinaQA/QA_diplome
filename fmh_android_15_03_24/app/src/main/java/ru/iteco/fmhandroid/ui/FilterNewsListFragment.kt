package ru.iteco.fmhandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.iteco.fmhandroid.R

class FilterNewsListFragment : Fragment() {

    private lateinit var categoryAutoCompleteTextView: MaterialAutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAutoCompleteTextView = view.findViewById(R.id.news_item_category_text_auto_complete_text_view)

        val newsCategories = listOf("Все категории", "Политика", "Спорт", "Культура")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            newsCategories
        )
        categoryAutoCompleteTextView.setAdapter(adapter)
    }
}
