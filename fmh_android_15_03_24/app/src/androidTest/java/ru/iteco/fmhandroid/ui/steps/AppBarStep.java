package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.WaitId;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.AppBarPage;
import ru.iteco.fmhandroid.ui.pages.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

public class AppBarStep extends DataGenerator {

    private final AppBarPage appBarPage;
    private final LoveIsAllPage loveIsAllPage;
    private final NewsPage newsPage;

    public AppBarStep() {
        this.appBarPage = new AppBarPage();
        this.loveIsAllPage = new LoveIsAllPage();
        this.newsPage = new NewsPage();
    }

    public void clickNavigationBtn() {
        WaitId.waitUntilElement(R.id.custom_app_bar_title_text_view);
        appBarPage.getNavigationButton().check(matches(isDisplayed()));
        appBarPage.getNavigationButton().perform(click());
    }

    public void clickNavigationNews() {
        WaitId.waitUntilElement(android.R.id.title);
        appBarPage.getNavigationNews().check(matches(isDisplayed()));
        appBarPage.getNavigationNews().perform(click());
    }

    public void clickNavigationAbout() {
        WaitId.waitUntilElement(android.R.id.title);
        appBarPage.getNavigationAbout().check(matches(isDisplayed()));
        appBarPage.getNavigationAbout().perform(click());
    }

    public void clickNavigationMain() {
        WaitId.waitUntilElement(android.R.id.title);
        appBarPage.getNavigationMain().check(matches(isDisplayed()));
        appBarPage.getNavigationMain().perform(click());
    }

    public void clickLoveIsAllBtn() {
        WaitId.waitUntilElement(R.id.our_mission_image_button);
        appBarPage.getLoveIsAllBtn().check(matches(isDisplayed()));
        appBarPage.getLoveIsAllBtn().perform(click());
    }

    public void checkLoveIsAllTitle() {
        WaitId.waitUntilElement(R.id.our_mission_title_text_view);
        loveIsAllPage.getLoveIsAllTitle().check(matches(isDisplayed()));
        loveIsAllPage.getLoveIsAllTitle().check(matches(withText(loveTitle)));
    }

    public void checkNewsPageTitle() {
        WaitId.waitUntilElement(R.id.container_list_news_include);
        newsPage.newsPageTitle().check(matches(isDisplayed()));
        newsPage.newsPageTitle().check(matches(withText(newsPageTitle)));
    }

    public void checkNewsTitle() {
        WaitId.waitUntilElement(R.id.container_list_news_include);
        newsPage.newsPageTitle().check(matches(isDisplayed()));
    }

    public void exit() {
        WaitId.waitUntilElement(R.id.authorization_image_button);
        appBarPage.getExitButton().check(matches(isDisplayed()));
        appBarPage.getExitButton().perform(click());
        WaitId.waitUntilElement(android.R.id.title);
        appBarPage.getLogOutText().check(matches(isDisplayed()));
        appBarPage.getLogOutText().perform(click());
    }
}
