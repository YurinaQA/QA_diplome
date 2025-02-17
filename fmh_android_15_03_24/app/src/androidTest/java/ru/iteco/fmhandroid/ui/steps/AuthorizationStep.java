package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.AuthorizationText;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginButton;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.loginFieldAsTextField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.passwordField;
import static ru.iteco.fmhandroid.ui.pages.AuthorizationPage.passwordFieldAsTextField;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class AuthorizationStep extends DataGenerator {
    //Заполнение поля логина
    public static void loginFieldInput(String input) {
        waitUntilElement(R.id.login_text_input_layout);
        loginFieldAsTextField.perform(click());
        loginFieldAsTextField.perform(replaceText(input), closeSoftKeyboard());
        loginField.check(matches(isDisplayed()));
    }

    //Заполнение поля пароля
    public static void passwordFieldInput(String input) {
        passwordFieldAsTextField.perform(click());
        passwordFieldAsTextField.perform(replaceText(input), closeSoftKeyboard());
        passwordField.check(matches(isDisplayed()));
    }

    //Нажатие кнопки входа
    public static void clickLoginBtn () {
        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());
    }

    //Проверка отображения заголовка "Authorization"
    public static void checkAuthorizationPage () {
        waitUntilElement("Authorization");
        //waitUntilElement(R.id.nav_host_fragment);
        AuthorizationText.check(matches(isDisplayed()));
    }

}
