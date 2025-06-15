package ru.iteco.fmhandroid.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.databinding.FragmentCreateEditNewsBinding
import ru.iteco.fmhandroid.dto.News
import ru.iteco.fmhandroid.utils.Utils
import ru.iteco.fmhandroid.utils.Utils.convertNewsCategory
import ru.iteco.fmhandroid.utils.Utils.saveDateTime
import ru.iteco.fmhandroid.utils.Utils.updateDateLabel
import ru.iteco.fmhandroid.utils.Utils.updateTimeLabel
import ru.iteco.fmhandroid.viewmodel.NewsControlPanelViewModel
import java.time.Instant.now
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@AndroidEntryPoint
class CreateEditNewsFragment : Fragment(R.layout.fragment_create_edit_news) {
    private val viewModel: NewsControlPanelViewModel by viewModels()
    private val args: CreateEditNewsFragmentArgs by navArgs()

    private lateinit var vPublishDatePicker: TextInputEditText
    private lateinit var vPublishTimePicker: TextInputEditText
    private lateinit var binding: FragmentCreateEditNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        lifecycleScope.launch {
            viewModel.saveNewsItemExceptionEvent.collect {
                showErrorToast(R.string.error_saving)
            }
        }
        lifecycleScope.launch {
            viewModel.editNewsItemExceptionEvent.collect {
                showErrorToast(R.string.error_saving)
            }
        }
        lifecycleScope.launch {
            viewModel.newsItemCreatedEvent.collect {
                findNavController().navigateUp()
            }
        }
        lifecycleScope.launch {
            viewModel.editNewsItemSavedEvent.collect {
                findNavController().navigateUp()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateEditNewsBinding.bind(view)

        with(binding) {
            containerCustomAppBarIncludeOnFragmentCreateEditNews.apply {
                mainMenuImageButton.visibility = View.GONE
                authorizationImageButton.visibility = View.GONE
                ourMissionImageButton.visibility = View.GONE
                trademarkImageView.visibility = View.GONE
            }

            newsItemCategoryTextInputLayout.isStartIconVisible = false

            if (args.newsItemArg == null) {
                containerCustomAppBarIncludeOnFragmentCreateEditNews.customAppBarTitleTextView.apply {
                    visibility = View.VISIBLE
                    setText(R.string.creating)
                    textSize = 18F
                }
                containerCustomAppBarIncludeOnFragmentCreateEditNews.customAppBarSubTitleTextView.apply {
                    visibility = View.VISIBLE
                    setText(R.string.news)
                }
            } else {
                containerCustomAppBarIncludeOnFragmentCreateEditNews.customAppBarTitleTextView.apply {
                    visibility = View.VISIBLE
                    setText(R.string.editing)
                    textSize = 18F
                }
                containerCustomAppBarIncludeOnFragmentCreateEditNews.customAppBarSubTitleTextView.apply {
                    visibility = View.VISIBLE
                    setText(R.string.news)
                }
            }

            args.newsItemArg?.let { newsItem ->
                newsItemCategoryTextAutoCompleteTextView.setText(newsItem.category.name)
                newsItemTitleTextInputEditText.setText(newsItem.newsItem.title)
                newsItemPublishDateTextInputEditText.setText(Utils.formatDate(newsItem.newsItem.publishDate))
                newsItemPublishTimeTextInputEditText.setText(Utils.formatTime(newsItem.newsItem.publishDate))
                newsItemDescriptionTextInputEditText.setText(newsItem.newsItem.description)
                switcher.isChecked = newsItem.newsItem.publishEnabled
            }

            if (args.newsItemArg == null) {
                switcher.isChecked = true
                switcher.isEnabled = false
            }

            updateSwitcherText()

            switcher.setOnClickListener {
                updateSwitcherText()
            }

            cancelButton.setOnClickListener {
                val activity = activity ?: return@setOnClickListener
                AlertDialog.Builder(activity)
                    .setMessage(R.string.cancellation)
                    .setPositiveButton(R.string.fragment_positive_button) { dialog, _ ->
                        dialog.dismiss()
                        findNavController().navigateUp()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }

            saveButton.setOnClickListener {
                if (newsItemCategoryTextAutoCompleteTextView.text.isNullOrBlank() ||
                    newsItemTitleTextInputEditText.text.isNullOrBlank() ||
                    newsItemPublishDateTextInputEditText.text.isNullOrBlank() ||
                    newsItemPublishTimeTextInputEditText.text.isNullOrBlank() ||
                    newsItemDescriptionTextInputEditText.text.isNullOrBlank()
                ) {
                    emptyFieldWarning()
                    showErrorToast(R.string.empty_fields)
                } else {
                    fillNewsItem()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getAllNewsCategories().collect { categories ->
                val newsCategoryItems = categories.map { it.name }

                with(binding) {
                    val adapter = ArrayAdapter(requireContext(), R.layout.menu_item, newsCategoryItems)
                    newsItemCategoryTextAutoCompleteTextView.setAdapter(adapter)

                    newsItemCategoryTextAutoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
                        val selectedItem = parent.getItemAtPosition(position).toString()
                        val title = newsItemTitleTextInputEditText
                        if (title.text.isNullOrBlank() || newsCategoryItems.contains(title.text.toString())) {
                            title.setText(selectedItem)
                        }
                    }
                }
            }
        }

        val calendar = Calendar.getInstance()

        vPublishDatePicker = binding.newsItemPublishDateTextInputEditText

        val publishDatePicker =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateLabel(calendar, vPublishDatePicker)
            }

        vPublishDatePicker.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                publishDatePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                this.datePicker.minDate = System.currentTimeMillis() - 1000
            }.show()
        }

        vPublishTimePicker = binding.newsItemPublishTimeTextInputEditText

        val publishTimePicker = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updateTimeLabel(calendar, vPublishTimePicker)
        }

        vPublishTimePicker.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                publishTimePicker,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun updateSwitcherText() {
        if (binding.switcher.isChecked) {
            binding.switcher.setText(R.string.news_item_active)
        } else {
            binding.switcher.setText(R.string.news_item_not_active)
        }
    }

    private fun FragmentCreateEditNewsBinding.emptyFieldWarning() {
        newsItemCategoryTextInputLayout.isStartIconVisible =
            newsItemCategoryTextAutoCompleteTextView.text.isNullOrBlank()

        newsItemTitleTextInputLayout.endIconMode =
            if (newsItemTitleTextInputEditText.text.isNullOrBlank()) TextInputLayout.END_ICON_CUSTOM else TextInputLayout.END_ICON_NONE

        newsItemCreateDateTextInputLayout.endIconMode =
            if (newsItemPublishDateTextInputEditText.text.isNullOrBlank()) TextInputLayout.END_ICON_CUSTOM else TextInputLayout.END_ICON_NONE

        newsItemPublishTimeTextInputLayout.endIconMode =
            if (newsItemPublishTimeTextInputEditText.text.isNullOrBlank()) TextInputLayout.END_ICON_CUSTOM else TextInputLayout.END_ICON_NONE

        newsItemDescriptionTextInputLayout.endIconMode =
            if (newsItemDescriptionTextInputEditText.text.isNullOrBlank()) TextInputLayout.END_ICON_CUSTOM else TextInputLayout.END_ICON_NONE
    }

    private fun showErrorToast(text: Int) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun fillNewsItem() {
        with(binding) {
            val news = args.newsItemArg
            if (news != null) {
                val editedNews = News(
                    id = news.newsItem.id,
                    title = newsItemTitleTextInputEditText.text.toString(),
                    newsCategoryId = convertNewsCategory(newsItemCategoryTextAutoCompleteTextView.text.toString()),
                    creatorName = news.newsItem.creatorName,
                    createDate = news.newsItem.createDate,
                    creatorId = news.newsItem.creatorId,
                    publishDate = saveDateTime(
                        newsItemPublishDateTextInputEditText.text.toString(),
                        newsItemPublishTimeTextInputEditText.text.toString()
                    ),
                    description = newsItemDescriptionTextInputEditText.text.toString(),
                    publishEnabled = switcher.isChecked
                )
                viewModel.edit(editedNews)
            } else {
                val createdNews = News(
                    id = null,
                    title = newsItemTitleTextInputEditText.text.toString().trim(),
                    newsCategoryId = convertNewsCategory(newsItemCategoryTextAutoCompleteTextView.text.toString()),
                    creatorName = Utils.fullUserNameGenerator(
                        viewModel.currentUser.lastName,
                        viewModel.currentUser.firstName,
                        viewModel.currentUser.middleName
                    ),
                    createDate = LocalDateTime.now()
                        .toEpochSecond(ZoneId.of("Europe/Moscow").rules.getOffset(now())),
                    creatorId = viewModel.currentUser.id,
                    publishDate = saveDateTime(
                        newsItemPublishDateTextInputEditText.text.toString(),
                        newsItemPublishTimeTextInputEditText.text.toString()
                    ),
                    description = newsItemDescriptionTextInputEditText.text.toString().trim(),
                    publishEnabled = switcher.isChecked
                )
                viewModel.save(createdNews)
            }
        }
    }
}
