package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.exitBtn;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.logOut;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginButton;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginFieldAsTextField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.passwordField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.passwordFieldAsTextField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.titleTextElement;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки Авторизация")
public class AppActivityTest extends DataGenerator {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void loginAuth() {
        try {
            waitUntilElement(R.id.nav_host_fragment);
            titleTextElement.check(matches(isDisplayed()));
        } catch (androidx.test.espresso.NoMatchingViewException e) {
            waitUntilElement(R.id.authorization_image_button);
            exitBtn.check(matches(isDisplayed()));
            exitBtn.perform(click());
            waitUntilElement(android.R.id.title);
            logOut.perform(click());
        }
    }

    @After
    public void loginOut() {
        try {
            waitUntilElement(R.id.nav_host_fragment);
            titleTextElement.check(matches(isDisplayed()));
        } catch (androidx.test.espresso.NoMatchingViewException e) {
            waitUntilElement(R.id.authorization_image_button);
            exitBtn.check(matches(isDisplayed()));
            exitBtn.perform(click());
            waitUntilElement(android.R.id.title);
            logOut.perform(click());
        }
    }

    @Test
    @Story("Авторизация валидными данными")
    public void ValidAuthorization() {
        waitUntilElement(R.id.login_text_input_layout);
        loginFieldAsTextField.perform(click());
        loginFieldAsTextField.perform(replaceText(validLogin), closeSoftKeyboard());
        loginField.check(matches(isDisplayed()));

        passwordFieldAsTextField.perform(click());
        passwordFieldAsTextField.perform(replaceText(validPassword), closeSoftKeyboard());
        passwordField.check(matches(isDisplayed()));

        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());

        //waitUntilElement(R.id.container_list_news_include_on_fragment_main);
        //textView.check(matches(isDisplayed()));

    }

//    @Test
//    @Story("Авторизация невалидным логином")
//    public void InvalidAuthorization() {
//        waitUntilElement(R.id.login_text_input_layout);
//        loginFieldAsTextField.perform(click());
//        loginFieldAsTextField.perform(replaceText(invalidInput), closeSoftKeyboard());
//        loginField.check(matches(isDisplayed()));
//
//        passwordFieldAsTextField.perform(click());
//        passwordFieldAsTextField.perform(replaceText(validPassword), closeSoftKeyboard());
//        passwordField.check(matches(isDisplayed()));
//
//        loginButton.check(matches(isDisplayed()));
//        loginButton.perform(click());
//        waitUntilElement("Something went wrong. Try again latter");
//        //wrongInput.check(matches(isDisplayed()));
//    }

//    @Test
//    @Story("Авторизация невалидным пароль")
//    public void InvalidAuthorization2() {
//        waitUntilElement(R.id.login_text_input_layout);
//        loginFieldAsTextField.perform(click());
//        loginFieldAsTextField.perform(replaceText(validLogin), closeSoftKeyboard());
//        loginField.check(matches(isDisplayed()));
//
//        passwordFieldAsTextField.perform(click());
//        passwordFieldAsTextField.perform(replaceText(invalidInput), closeSoftKeyboard());
//        passwordField.check(matches(isDisplayed()));
//
//        loginButton.check(matches(isDisplayed()));
//        loginButton.perform(click());
//
//        waitUntilElement(R.id.container_list_news_include_on_fragment_main);
//        ViewInteraction WrongText = onView(withId(R.id.container_list_news_include_on_fragment_main));
//        WrongText.check(matches(isDisplayed()));
//        //textView.check(matches(withText("News")));
//    }
}
