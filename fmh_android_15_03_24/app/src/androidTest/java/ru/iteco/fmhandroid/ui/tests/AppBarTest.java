package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.AppBarPage.navigationAbout;

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
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppBarTest extends DataGenerator {

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
    @Story("Переход в раздел 'News' из раздела 'Main' ")
    public void ShouldGoToNewsFromMain() {
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationNews();
        //Assert
        AppBarStep.checkNewsPageTitle();
    }

    @Test
    @Story("Переход в раздел 'About' из раздела 'Main' ")
    public void ShouldGoToAboutFromMain() {
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationAbout();
        //Assert
        AboutStep.checkVersionTitle();

    }

    @Test
    @Story("Переход в раздел 'About' из раздела 'News' ")
    public void ShouldGoToAboutFromNews() {
        ShouldGoToNewsFromMain();
        AppBarStep.clickNavigationBtn();
        //Assert
        waitUntilElement(android.R.id.title);
        navigationAbout.check(matches(isNotClickable()));
    }

    @Test
    @Story("Переход в раздел 'Main' из раздела 'News' ")
    public void ShouldGoToMainFromNews() {
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationNews();
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationMain();
        //Assert
        MainStep.checkNewsTitle();
    }

    @Test
    @Story("Переход на страницу 'Love ia All")
    public void ShouldGoToLoveIsAll() {
        AppBarStep.clickLoveIsAllBtn();
        //Assert
        AppBarStep.checkLoveIsAllBtn();
    }

    @Test
    @Story("Выход из приложения")
    public void ShouldExit() {
        AppBarStep.exit();
        //Assert
        AuthorizationStep.checkAuthorizationPage();
    }

}
