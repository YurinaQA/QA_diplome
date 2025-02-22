package ru.iteco.fmhandroid.ui.tests;


import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
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
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest extends DataGenerator {
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void loginAuth() {
        try {
            MainStep.checkNewsTitle();
        } catch (androidx.test.espresso.PerformException e) {
            AuthorizationStep.loginFieldInput(validLogin);
            AuthorizationStep.passwordFieldInput(validPassword);
            AuthorizationStep.clickLoginBtn();
        }
        MainStep.checkNewsTitle();
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationNews();
        NewsStep.checkNewsPageTitle();
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @Story("Сворачивание/разворачивание описания новостей на странице 'News'")
    public void shoulOpenNewsBox() {
        NewsStep.openNewsBox(number);
        //Assert
        NewsStep.checkNewsBox(number);
    }

    @Test
    @Story("Обновление страницы  'News' нажатием кнопкой 'Refresh'")
    public void shouldRefrashByBtn() {
        NewsStep.goToFilterSection();
        NewsStep.selectCategoryFiler(category_2);
        NewsStep.clickFilterBtn();
        //Assert
        try {
            NewsStep.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            NewsStep.thereIsntNews();
            NewsStep.clickRefrashBtn();
            NewsStep.thereIsntNews();
        }
    }

    @Test
    @Story("Выбор каждой категории из выпадающего списка в разделе 'Filter news'")
    public void shoulGoToFilterSection() {
        NewsStep.goToFilterSection();
        NewsStep.selectCategoryFiler(category_1);
        NewsStep.clickFilterBtn();
        //Assert
        try {
            NewsStep.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            NewsStep.thereIsntNews();
        }
    }

    @Test
    @Story("Ввод в поле 'Category' категории не из списка в разделе 'Filter news'")
    public void shouldFilterWithInputTextToCategory() {
        NewsStep.goToFilterSection();
        NewsStep.clickOnCategory();
        NewsStep.inputText(wrongText);
        NewsStep.clickFilterBtn();
        //Assert
        try {
            NewsStep.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            NewsStep.thereIsntNews();
        }
    }

    @Test
    @Story("Оставление поле 'Category' пустым на странице 'Filter news' ")
    public void shouldFilterWithEmptyCategory() {
        NewsStep.goToFilterSection();
        NewsStep.clickFilterBtn();
        //Assert
        try {
            NewsStep.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            NewsStep.thereIsntNews();
        }
    }

    @Test
    @Story("Закрытие раздела 'Filter news' кнопкой 'Cancel'")
    public void shouldCloseFilterSection() {
        NewsStep.goToFilterSection();
        NewsStep.clickCancelFilter();
        //Assert
        NewsStep.checkNewsPageTitle();
    }

    @Test
    @Story("Удаление новости в разделе 'Control panel'")
    public void shouldDeleteNews() {
        NewsStep.openControlPanelSection();
        NewsStep.checkControlPanelSectionTitle(controlPanelTitle);
        //try {
        NewsStep.checkCPNewsCard();
        //} catch (NoMatchingViewException e) {
        //}
        NewsStep.CPDeleteNews();
        NewsStep.checkDeleteCard();
    }

    @Test
    @Story("Открытие раздела 'Editing News'")
    public void shouldOpenEditigNews() {
        NewsStep.openControlPanelSection();
        NewsStep.checkControlPanelSectionTitle(controlPanelTitle);
        NewsStep.checkCPNewsCard();
        NewsStep.openEditingSection();
        NewsStep.checkEditingSectionTitle();
    }

    @Test
    @Story("Появление предупреждения в случае пустого поля 'Category' в разделе 'Editing news'")
    public void shouldOpenMassageCategory() {
        shouldOpenEditigNews();
        NewsStep.CategoryEN("");
        NewsStep.clickSaveBtn();
        //Assert
        AuthorizationPage.errorMessageText("Fill empty fields", decorView);
    }

    @Test
    @Story("Изменение заголовка в разделе 'Editing News'")
    public void shouldEditTitleEN() {
        shouldOpenEditigNews();
        NewsStep.replaceTitleEN(textTitle);
        NewsStep.clickSaveBtn();
        //Assert
        NewsStep.checkTitleEN(textTitle);
    }

    @Test
    @Story("Изменение заголовка в разделе 'Editing News'")
    public void shouldOpenMassageTitle() {
        shouldOpenEditigNews();
        NewsStep.replaceTitleEN("");
        NewsStep.clickSaveBtn();
        //Assert
        AuthorizationPage.errorMessageText("Fill empty fields", decorView);
    }

    @Test
    @Story("Изменение даты в разделе 'Editing News'")
    public void shouldEditDateEN() {
        shouldOpenEditigNews();
        NewsStep.DateFieldSet(futureDate(1));
        NewsStep.clickSaveBtn();
        //Assert
        NewsStep.checkDateEN(futureDate(1));
    }


    @Test
    @Story("Изменение описания в разделе 'Editing News'")
    public void shouldEditDiscriptionEN() {
        shouldOpenEditigNews();
        NewsStep.DiscriptionSet(textTitle);
        NewsStep.clickSaveBtn();
        //Assert
        NewsStep.checkDiscription(textTitle);
    }

    @Test
    @Story("Изменение статуса в разделе 'Editing News'")
    public void shouldEditStatusEN() {
        shouldOpenEditigNews();
        NewsStep.SwitcherTurn();
        NewsStep.clickSaveBtn();
        //Assert
        try {
            NewsStep.checkStatus(statusActive);
        } catch (NoMatchingViewException e) {
            NewsStep.checkStatus(statusNotActive);
        }
    }

    @Test
    @Story("Появление модального окна с предупреждением о потерянных данных после нажатия " +
            "кнопки 'Cancel' в разделе 'Editing news'")
    public void shouldCloseEditingNewsEN() {
        shouldOpenEditigNews();
        NewsStep.clickCancelBtn();
        NewsStep.checkMessage();
        //Assert
        NewsStep.checkControlPanelSectionTitle(controlPanelTitle);
    }

    @Test
    @Story("Открытие раздела 'Creating news")
    public void shouldOpenCreatingNewsSection() {
        NewsStep.openControlPanelSection();
        NewsStep.checkControlPanelSectionTitle(controlPanelTitle);
        NewsStep.openCreatingNews();
        //Assert
        NewsStep.checkCreatingNewsTitle();
    }

    @Test
    @Story("Создание новости в 'Creating news")
    public void shouldInputCategoryCN() {
        shouldOpenCreatingNewsSection();
        NewsStep.selectCategoryFiler(category_1);
        NewsStep.emptyTitleEN(newNews);
        NewsStep.DateFieldSet(futureDate(1));
        NewsStep.TimeFieldSet(futureTimeMinute(1));
        NewsStep.DiscriptionSet(newNews);
        NewsStep.clickSaveBtn();
        //Assert
        NewsStep.checkNewCreatingNewsTitle(newNews);
    }

    @Test
    @Story("Появление сообщения при незаполненом поле 'Creating news")
    public void shouldMessageAbutEmptyFieldsCN() {
        shouldOpenCreatingNewsSection();
        NewsStep.selectCategoryFiler(category_1);
        NewsStep.emptyTitleEN(newNews);
        NewsStep.DateFieldSet(futureDate(1));
        NewsStep.TimeFieldSet(futureTimeMinute(1));
        NewsStep.clickSaveBtn();
        //Assert
        AuthorizationPage.errorMessageText("Fill empty fields", decorView);
    }

    @Test
    @Story("Переключение статуса 'Active'/'Not active' в разделе 'Creating news'")
    public void shouldSwitchCN() {
        shouldOpenCreatingNewsSection();
        NewsStep.selectCategoryFiler(category_1);
        NewsStep.emptyTitleEN(newNews);
        NewsStep.DateFieldSet(futureDate(1));
        NewsStep.TimeFieldSet(futureTimeMinute(1));
        NewsStep.DiscriptionSet(newNews);
        //Assert
        NewsStep.checkSwitchCN();

    }

}
