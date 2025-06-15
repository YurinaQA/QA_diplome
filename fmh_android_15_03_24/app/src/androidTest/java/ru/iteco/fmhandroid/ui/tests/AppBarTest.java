package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppBarTest extends DataGenerator {

    private final AuthorizationStep authorizationStep = new AuthorizationStep();
    private final AppBarStep appBarStep = new AppBarStep();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        try {
            appBarStep.checkNewsPageTitle();
        } catch (Exception e) {
            authorizationStep.loginFieldInput(validLogin);
            authorizationStep.passwordFieldInput(validPassword);
            authorizationStep.clickLoginBtn();
        }
        appBarStep.checkNewsPageTitle();
    }

    @Test
    public void testNavigationAboutIsClickable() {
        appBarStep.clickNavigationBtn();
        appBarStep.clickNavigationAbout();
    }

    @Test
    public void testNavigationNews() {
        appBarStep.clickNavigationBtn();
        appBarStep.clickNavigationNews();
        appBarStep.checkNewsPageTitle();
    }

    @Test
    public void testNavigationMain() {
        appBarStep.clickNavigationBtn();
        appBarStep.clickNavigationMain();
        // Проверка, что главная страница открыта (нужно добавить метод проверки в AppBarStep)
    }

    @Test
    public void testLoveIsAllButton() {
        appBarStep.clickLoveIsAllBtn();
        appBarStep.checkLoveIsAllTitle();
    }

    @Test
    public void testExitApp() {
        appBarStep.exit();
        authorizationStep.checkAuthorizationPage();
    }
}
