package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.WaitId;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;

/**
 * Шаги для авторизации в приложении.
 */
public class AuthorizationStep {

    private final AuthorizationPage authorizationPage;

    public AuthorizationStep() {
        this.authorizationPage = new AuthorizationPage();
    }

    /**
     * Ввод текста в поле логина.
     * @param input Текст для ввода в поле логина.
     */
    public void loginFieldInput(String input) {
        WaitId.waitUntilElement(R.id.login_text_input_layout);
        authorizationPage.getLoginTextField().perform(click());
        authorizationPage.getLoginTextField().perform(replaceText(input), closeSoftKeyboard());
        authorizationPage.getLoginFieldLayout().check(matches(isDisplayed()));
    }

    /**
     * Ввод текста в поле пароля.
     * @param input Текст для ввода в поле пароля.
     */
    public void passwordFieldInput(String input) {
        WaitId.waitUntilElement(R.id.password_text_input_layout);
        authorizationPage.getPasswordTextField().perform(click());
        authorizationPage.getPasswordTextField().perform(replaceText(input), closeSoftKeyboard());
        authorizationPage.getPasswordFieldLayout().check(matches(isDisplayed()));
    }

    /**
     * Нажатие кнопки входа.
     */
    public void clickLoginBtn() {
        authorizationPage.getLoginButton().check(matches(isDisplayed()));
        authorizationPage.getLoginButton().perform(click());
    }

    /**
     * Проверка отображения заголовка страницы "Authorization".
     */
    public void checkAuthorizationPage() {
        WaitId.waitUntilElement("Authorization");
        authorizationPage.getAuthorizationText().check(matches(isDisplayed()));
    }
}
