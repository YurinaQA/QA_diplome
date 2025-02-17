package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {

    //Сворачивание/разворачивание ленты новостей
    public static ViewInteraction openNewsBtn = onView(withId(R.id.expand_material_button));
    //public static ViewInteraction openNewsBtn = onView(withId(R.id.container_list_news_include_on_fragment_main));

    //Проверка отображения "All news"
    public static ViewInteraction allNewsText = onView(
            allOf(withId(R.id.all_news_text_view), withText("ALL NEWS")));

    //Переход на страницу новостей через "All news"
    public static ViewInteraction allNewsBtn = onView(
            allOf(withId(R.id.all_news_text_view), withText("ALL NEWS")));

    ////Текст заголовка новостной ленты на главной странице
    public static ViewInteraction newsFeedTitle = onView(withId(R.id.container_list_news_include_on_fragment_main));

}
