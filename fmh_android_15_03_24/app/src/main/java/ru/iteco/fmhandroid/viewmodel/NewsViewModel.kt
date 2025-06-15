package ru.iteco.fmhandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.iteco.fmhandroid.adapter.OnNewsItemClickListener
import ru.iteco.fmhandroid.dto.User
import ru.iteco.fmhandroid.dto.News
import ru.iteco.fmhandroid.repository.newsRepository.NewsRepository
import ru.iteco.fmhandroid.repository.userRepository.UserRepository
import ru.iteco.fmhandroid.ui.viewdata.NewsViewData
import ru.iteco.fmhandroid.utils.Utils
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository
) : ViewModel(), OnNewsItemClickListener {

    private val sortDirection = MutableStateFlow(SortDirection.ASC)

    private val clearFilter = Filter(
        newsCategoryId = null,
        dateStart = null,
        dateEnd = null
    )

    private val filterFlow = MutableStateFlow(clearFilter)

    private val openNewsIds = MutableStateFlow<Set<Int>>(emptySet())

    val loadNewsExceptionEvent = MutableSharedFlow<Unit>()
    val loadNewsCategoriesExceptionEvent = MutableSharedFlow<Unit>()
    val newsListUpdatedEvent = MutableSharedFlow<Unit>()

    val currentUser: User
        get() = userRepository.currentUser

    // Получаем поток категорий один раз и используем его в combine
    private val categoriesFlow = newsRepository.getAllNewsCategories()
        .catch { e ->
            e.printStackTrace()
            loadNewsCategoriesExceptionEvent.emit(Unit)
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    @ExperimentalCoroutinesApi
    val data: Flow<List<NewsViewData>> = filterFlow.flatMapLatest { filter ->
        newsRepository.getAllNews(
            viewModelScope,
            publishEnabled = true,
            publishDateBefore = Utils.fromLocalDateTimeToTimeStamp(LocalDateTime.now()),
            newsCategoryId = filter.newsCategoryId,
            dateStart = filter.dateStart,
            dateEnd = filter.dateEnd
        )
    }.combine(categoriesFlow) { newsList, categoriesList ->
        val categoriesMap = categoriesList.associateBy { it.id }
        newsList to categoriesMap
    }.combine(sortDirection) { (newsList, categoriesMap), sortDir ->
        val sortedList = when (sortDir) {
            SortDirection.ASC -> newsList
            SortDirection.DESC -> newsList.reversed()
        }
        sortedList to categoriesMap
    }.combine(openNewsIds) { (newsList, categoriesMap), openIds ->
        newsList.map { newsEntity ->
            val id = requireNotNull(newsEntity.id) { "News id must not be null" }
            NewsViewData(
                id = id,
                category = categoriesMap[newsEntity.newsCategoryId] ?: run {
                    // Если категория не найдена, возвращаем Unknown с типом Unknown
                    News.Category(
                        id = -1,
                        name = "Unknown",
                        deleted = false,
                        type = News.Category.Type.Unknown
                    )
                },
                title = newsEntity.title,
                description = newsEntity.description,
                creatorId = newsEntity.creatorId,
                creatorName = newsEntity.creatorName,
                createDate = newsEntity.createDate,
                publishDate = newsEntity.publishDate,
                publishEnabled = newsEntity.publishEnabled,
                isOpen = openIds.contains(id)
            )
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            internalOnRefresh()
        }
    }

    private suspend fun internalOnRefresh() {
        try {
            newsRepository.refreshNews()
            newsListUpdatedEvent.emit(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            loadNewsExceptionEvent.emit(Unit)
        }
    }

    fun onSortDirectionButtonClicked() {
        sortDirection.value = sortDirection.value.reverse()
    }

    suspend fun getAllNewsCategories() =
        newsRepository.getAllNewsCategories()
            .catch { e ->
                e.printStackTrace()
                loadNewsCategoriesExceptionEvent.emit(Unit)
            }

    fun onFilterNewsClicked(
        newsCategoryId: Int?,
        dateStart: Long?,
        dateEnd: Long?
    ) {
        filterFlow.value = Filter(
            newsCategoryId = newsCategoryId,
            dateStart = dateStart,
            dateEnd = dateEnd
        )
    }

    fun initializationListNewsCategories(listNewsCategories: List<News.Category>) {
        viewModelScope.launch {
            newsRepository.saveNewsCategories(listNewsCategories)
        }
    }

    enum class SortDirection {
        ASC,
        DESC;

        fun reverse() = when (this) {
            ASC -> DESC
            DESC -> ASC
        }
    }

    private data class Filter(
        val newsCategoryId: Int?,
        val dateStart: Long?,
        val dateEnd: Long?
    )

    override fun onCard(newsItem: NewsViewData) {
        openNewsIds.update { currentSet ->
            if (currentSet.contains(newsItem.id)) currentSet - newsItem.id
            else currentSet + newsItem.id
        }
    }
}
