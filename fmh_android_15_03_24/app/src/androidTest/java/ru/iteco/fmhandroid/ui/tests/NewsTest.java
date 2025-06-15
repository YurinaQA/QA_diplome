package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest extends DataGenerator {

    private View decorView;

    private final AuthorizationStep authorizationStep = new AuthorizationStep();
    private final AppBarStep appBarStep = new AppBarStep();
    private final NewsStep newsStep = new NewsStep();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void loginAuth() {
        try {
            appBarStep.checkNewsPageTitle();
        } catch (PerformException e) {
            authorizationStep.loginFieldInput(validLogin);
            authorizationStep.passwordFieldInput(validPassword);
            authorizationStep.clickLoginBtn();
        }
        appBarStep.checkNewsPageTitle();
        appBarStep.clickNavigationBtn();
        appBarStep.clickNavigationNews();
        newsStep.checkNewsPageTitleDisplayed();
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @Story("Обновление страницы 'News' нажатием кнопки 'Refresh'")
    public void shouldRefreshByBtn() {
        newsStep.openFilterNewsSection();
        newsStep.selectCategory(category_2);
        newsStep.clickFilterButton();
        try {
            newsStep.checkFilterResults();
        } catch (PerformException e) {
            newsStep.checkNoNewsMessageDisplayed();
            newsStep.clickRefreshButton();
            newsStep.checkNoNewsMessageDisplayed();
        }
    }

    @Test
    @Story("Выбор каждой категории из выпадающего списка в разделе 'Filter news'")
    public void shouldGoToFilterSection() {
        newsStep.openFilterNewsSection();
        newsStep.selectCategory(category_1);
        newsStep.clickFilterButton();
        try {
            newsStep.checkFilterResults();
        } catch (PerformException e) {
            newsStep.checkNoNewsMessageDisplayed();
        }
    }

    @Test
    @Story("Ввод в поле 'Category' категории не из списка в разделе 'Filter news'")
    public void shouldFilterWithInputTextToCategory() {
        newsStep.openFilterNewsSection();
        newsStep.typeCustomCategory(wrongText);
        newsStep.clickFilterButton();
        try {
            newsStep.checkFilterResults();
        } catch (PerformException e) {
            newsStep.checkNoNewsMessageDisplayed();
        }
    }

    @Test
    @Story("Оставление поля 'Category' пустым на странице 'Filter news'")
    public void shouldFilterWithEmptyCategory() {
        newsStep.openFilterNewsSection();
        newsStep.clickFilterButton();
        try {
            newsStep.checkFilterResults();
        } catch (PerformException e) {
            newsStep.checkNoNewsMessageDisplayed();
        }
    }

    @Test
    @Story("Закрытие раздела 'Filter news' кнопкой 'Cancel'")
    public void shouldCloseFilterSection() {
        newsStep.openFilterNewsSection();
        newsStep.clickCancelFilterButton();
        newsStep.checkNewsPageTitleDisplayed();
    }
}
