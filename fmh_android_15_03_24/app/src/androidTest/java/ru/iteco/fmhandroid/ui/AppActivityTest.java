package ru.iteco.fmhandroid.ui;

//import io.qameta.allure.kotlin.Epic;
//import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;

import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginButton;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.passwordField;


import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.textView;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки Авторизация")
public class AppActivityTest extends DataGenerator {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    @Story("Авторизация валидными данными")
    public void ValidAuthorization() {
        loginField.check(matches(isDisplayed()));
        loginField.perform(replaceText(validLogin), closeSoftKeyboard());

        passwordField.check(matches(isDisplayed()));
        passwordField.perform(replaceText(validPassword), closeSoftKeyboard());

        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());

        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(MainTitle)));
    }

    @Test
    @Story("Авторизация невалидным логином")
    public void InvalidAuthorization() {
        loginField.check(matches(isDisplayed()));
        loginField.perform(replaceText(invalidInput), closeSoftKeyboard());

        passwordField.check(matches(isDisplayed()));
        passwordField.perform(replaceText(validPassword), closeSoftKeyboard());

        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());

        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(MainTitle)));
    }

    @Test
    @Story("Авторизация невалидным пароль")
    public void InvalidAuthorization2() {
        loginField.check(matches(isDisplayed()));
        loginField.perform(replaceText(validLogin), closeSoftKeyboard());

        passwordField.check(matches(isDisplayed()));
        passwordField.perform(replaceText(invalidInput), closeSoftKeyboard());

        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());

        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(MainTitle)));
    }


}
