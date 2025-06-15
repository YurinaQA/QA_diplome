package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.pages.NewsPage;

public class NewsStep {

    private final NewsPage newsPage = new NewsPage();

    public void checkNewsPageTitleDisplayed() {
        newsPage.newsPageTitle().check(matches(isDisplayed()));
    }

    public void clickRefreshButton() {
        newsPage.refreshBtn().perform(click());
    }

    public void openFilterNewsSection() {
        newsPage.filterNewsSection().perform(click());
        newsPage.filterNewsSectionTitle().check(matches(isDisplayed()));
    }

    public void selectCategory(String category) {
        newsPage.openCategoryList().perform(click());
        newsPage.selectCategoryFromList(category);
    }

    public void clickFilterButton() {
        newsPage.filterBtn().perform(click());
    }

    public void clickCancelFilterButton() {
        newsPage.cancelBtnFilter().perform(click());
    }

    public void checkNoNewsMessageDisplayed() {
        newsPage.notNewsYet().check(matches(isDisplayed()));
    }

    public void checkFilterResults() {
        newsPage.filteredNewsList().check(matches(isDisplayed()));
    }

    public void typeCustomCategory(String text) {
        newsPage.customCategoryInput().perform(typeText(text));
    }
}
