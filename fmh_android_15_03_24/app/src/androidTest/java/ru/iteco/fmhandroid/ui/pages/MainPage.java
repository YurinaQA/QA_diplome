package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {

    /**
     * Кнопка для сворачивания/разворачивания ленты новостей.
     */
    public ViewInteraction getOpenNewsButton() {
        return onView(withId(R.id.expand_material_button));
    }

    /**
     * Текст "ALL NEWS" для проверки отображения на главной странице.
     */
    public ViewInteraction getAllNewsText() {
        return onView(allOf(withId(R.id.all_news_text_view), withText("ALL NEWS")));
    }

    /**
     * Кнопка "ALL NEWS" для перехода на страницу новостей.
     */
    public ViewInteraction getAllNewsButton() {
        return onView(allOf(withId(R.id.all_news_text_view), withText("ALL NEWS")));
    }

    /**
     * Заголовок новостной ленты на главной странице.
     */
    public ViewInteraction getNewsFeedTitle() {
        return onView(withId(R.id.container_list_news_include_on_fragment_main));
    }
}
