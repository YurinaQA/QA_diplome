package ru.iteco.fmhandroid.ui.tests;


import android.view.View;

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

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationTest extends DataGenerator {
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void loginAuth() {
        try {
            AuthorizationStep.checkAuthorizationPage();
        } catch (androidx.test.espresso.PerformException e) {
            AppBarStep.exit();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

//    @After
//    public void loginOut() {
//        try {
//
//            waitUntilElement(R.id.nav_host_fragment);
//            AuthorizationText.check(matches(isDisplayed()));
//        } catch (androidx.test.espresso.NoMatchingViewException e) {
//            waitUntilElement(R.id.authorization_image_button);
//            exitBtn.check(matches(isDisplayed()));
//            exitBtn.perform(click());
//            waitUntilElement(android.R.id.title);
//            logOut.perform(click());
//        }
//    }

    @Test
    @Story("Авторизация валидными данными")
    public void ValidAuthorization() {
        AuthorizationStep.loginFieldInput(validLogin);
        AuthorizationStep.passwordFieldInput(validPassword);
        AuthorizationStep.clickLoginBtn();
        //Assert
        MainStep.checkNewsTitle();
    }

    @Test
    @Story("Авторизация невалидным логином")
    public void InvalidAuthorization() {

        AuthorizationStep.loginFieldInput(invalidInput);
        AuthorizationStep.passwordFieldInput(validPassword);
        AuthorizationStep.clickLoginBtn();
        ///Assert
        AuthorizationPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test
    @Story("Авторизация невалидным пароль")
    public void InvalidAuthorization2() {
        AuthorizationStep.loginFieldInput(validLogin);
        AuthorizationStep.passwordFieldInput(invalidInput);
        AuthorizationStep.clickLoginBtn();
        ///Assert
        AuthorizationPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }

    @Test
    @Story("Авторизация с пустыми полями логина и пароля")
    public void EmptyFieldAuthorization3() {
        AuthorizationStep.loginFieldInput("");
        AuthorizationStep.passwordFieldInput("");
        AuthorizationStep.clickLoginBtn();
        //Assert
        AuthorizationPage.errorMessageText("Login and password cannot be empty", decorView);
    }

    @Test
    @Story("Авторизация с полями логина и пароля заполнеными заглавными буквами")
    public void CapitalLetterAuthorization() {
        AuthorizationStep.loginFieldInput(capitalLogin);
        AuthorizationStep.passwordFieldInput(capitalPassword);
        AuthorizationStep.clickLoginBtn();
        //Assert
        AuthorizationPage.errorMessageText("Something went wrong. Try again later.", decorView);
    }
}
