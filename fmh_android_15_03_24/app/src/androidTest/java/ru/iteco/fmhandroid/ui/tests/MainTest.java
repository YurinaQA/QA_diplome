package ru.iteco.fmhandroid.ui.tests;


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
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest extends DataGenerator {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
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
    }

    @Test
    @Story("Разворачивание/сворачивание ленты новостей")
    public void ShouldCloseAndOpenNewsFeed() {
        MainStep.clickOpenNewsBtn();
        MainStep.clickOpenNewsBtn();
        //Assertion
        MainStep.checkAllNewsText();
    }

    @Test
    @Story("Переход на страницу новостей через 'All news' ")
    public void ShouldGoToNews() {
        MainStep.clickAllNewsText();
        //Assertion
        NewsStep.checkNewsPageTitle();
    }
}
