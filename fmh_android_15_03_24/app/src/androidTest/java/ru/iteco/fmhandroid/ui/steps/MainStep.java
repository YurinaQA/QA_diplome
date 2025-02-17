package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.MainPage.allNewsBtn;
import static ru.iteco.fmhandroid.ui.pages.MainPage.allNewsText;
import static ru.iteco.fmhandroid.ui.pages.MainPage.newsFeedTitle;
import static ru.iteco.fmhandroid.ui.pages.MainPage.openNewsBtn;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.NewsPageTitle;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class MainStep extends DataGenerator {
    //Сворачивание/разворачивание ленты новостей
    public static void clickOpenNewsBtn() {
        waitUntilElement(R.id.expand_material_button);
        openNewsBtn.check(matches(isDisplayed()));
        openNewsBtn.perform(click());
    }

    //Проверка отображения "All news"
    public static void checkAllNewsText() {
        waitUntilElement(R.id.all_news_text_view);
        allNewsText.check(matches(isDisplayed()));
        allNewsText.check(matches(withText(allNewsTitle)));
    }

    //Переход на страницу новостей через "All news"
    public static void clickAllNewsText() {
        waitUntilElement(R.id.all_news_text_view);
        allNewsBtn.check(matches(isDisplayed()));
        allNewsBtn.perform(click());
    }

    //Проверка заголовка на главной странице
    public static void checkNewsTitle() {
        waitUntilElement(R.id.container_list_news_include_on_fragment_main);
        newsFeedTitle.check(matches(isDisplayed()));
    }


}
