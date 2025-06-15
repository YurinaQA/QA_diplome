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

    private AuthorizationStep authorizationStep;
    private MainStep mainStep;
    private NewsStep newsStep;

    @Before
    public void loginAuth() {
        authorizationStep = new AuthorizationStep();
        mainStep = new MainStep();
        newsStep = new NewsStep();

        try {
            mainStep.checkNewsTitle();
        } catch (androidx.test.espresso.PerformException e) {
            authorizationStep.loginFieldInput(validLogin);
            authorizationStep.passwordFieldInput(validPassword);
            authorizationStep.clickLoginBtn();
        }
        mainStep.checkNewsTitle();
    }

    @Test
    @Story("Разворачивание/сворачивание ленты новостей")
    public void shouldCloseAndOpenNewsFeed() {
        mainStep.clickOpenNewsBtn();
        mainStep.clickOpenNewsBtn();
        mainStep.checkAllNewsText();
    }

    @Test
    @Story("Переход на страницу новостей через 'All news' ")
    public void shouldGoToNews() {
        mainStep.clickAllNewsText();
        newsStep.checkNewsPageTitleDisplayed();
    }
}
