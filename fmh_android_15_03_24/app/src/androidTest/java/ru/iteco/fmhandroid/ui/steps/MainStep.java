package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.WaitId;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.MainPage;

/**
 * Класс содержит шаги взаимодействия с главным экраном приложения.
 */
public class MainStep extends DataGenerator {

    private static final MainPage mainPage = new MainPage();

    /**
     * Нажатие на кнопку сворачивания/разворачивания ленты новостей.
     */
    public static void clickOpenNewsBtn() {
        WaitId.waitUntilElement(R.id.expand_material_button);
        mainPage.getOpenNewsButton().check(matches(isDisplayed()));
        mainPage.getOpenNewsButton().perform(click());
    }

    /**
     * Проверка отображения текста "All news" на экране.
     */
    public static void checkAllNewsText() {
        WaitId.waitUntilElement(R.id.all_news_text_view);
        mainPage.getAllNewsText().check(matches(isDisplayed()));
        mainPage.getAllNewsText().check(matches(withText(allNewsTitle)));
    }

    /**
     * Переход на страницу всех новостей через нажатие на "All news".
     */
    public static void clickAllNewsText() {
        WaitId.waitUntilElement(R.id.all_news_text_view);
        mainPage.getAllNewsButton().check(matches(isDisplayed()));
        mainPage.getAllNewsButton().perform(click());
    }

    /**
     * Проверка отображения заголовка ленты новостей на главной странице.
     */
    public static void checkNewsTitle() {
        WaitId.waitUntilElement(R.id.container_list_news_include_on_fragment_main);
        mainPage.getNewsFeedTitle().check(matches(isDisplayed()));
    }
}
