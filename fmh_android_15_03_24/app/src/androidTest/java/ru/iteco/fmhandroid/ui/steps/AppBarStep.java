package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.exitBtn;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.logOut;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.loveIsAllBtn;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.navigationAbout;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.navigationButton;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.navigationMain;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.navigationNews;
import static ru.iteco.fmhandroid.ui.pages.LoveIsAllPage.loveIsAllText;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.NewsPageTitle;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class AppBarStep extends DataGenerator {

    ////Нажатие на иконку "бургер"
    public static void clickNavigationBtn() {
        waitUntilElement(R.id.custom_app_bar_title_text_view);
        navigationButton.check(matches(isDisplayed()));
        navigationButton.perform(click());
    }

    //Нажатие на "News" в навигационном меню
    public static void clickNavigationNews() {
        waitUntilElement(android.R.id.title);
        navigationNews.check(matches(isDisplayed()));
        navigationNews.perform(click());
    }

    //Нажатие на "About" в навигационном меню
    public static void clickNavigationAbout() {
        waitUntilElement(android.R.id.title);
        navigationAbout.check(matches(isDisplayed()));
        navigationAbout.perform(click());
    }

    //Нажатие на "Main" в навигационном меню
    public static void clickNavigationMain() {
        waitUntilElement(android.R.id.title);
        navigationMain.check(matches(isDisplayed()));
        navigationMain.perform(click());
    }

    //Нажатие на иконку "бабочка"
    public static void clickLoveIsAllBtn() {
        waitUntilElement(R.id.our_mission_image_button);
        loveIsAllBtn.check(matches(isDisplayed()));
        loveIsAllBtn.perform(click());
    }

    //Проверка отображения заголовка "Love is all"
    public static void checkLoveIsAllBtn() {
        waitUntilElement(R.id.our_mission_title_text_view);
        loveIsAllText.check(matches(isDisplayed()));
        loveIsAllText.check(matches(withText(loveTitle)));
    }


    //Проверка отображения заголовка страницы "News"
    public static void checkNewsPageTitle() {
        waitUntilElement(R.id.container_list_news_include);
        NewsPageTitle.check(matches(isDisplayed()));
        NewsPageTitle.check(matches(withText(newsPageTitle)));
    }

    //Выход из приложения
    public static void exit() {
        waitUntilElement(R.id.authorization_image_button);
        exitBtn.check(matches(isDisplayed()));
        exitBtn.perform(click());
        waitUntilElement(android.R.id.title);
        logOut.perform(click());
    }

}
