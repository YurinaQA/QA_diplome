package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {

    public ViewInteraction getLoginFieldLayout() {
        return onView(withId(R.id.login_text_input_layout));
    }

    public ViewInteraction getLoginTextField() {
        return onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    }

    public ViewInteraction getPasswordFieldLayout() {
        return onView(withId(R.id.password_text_input_layout));
    }

    public ViewInteraction getPasswordTextField() {
        return onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    }

    public ViewInteraction getLoginButton() {
        return onView(withId(R.id.enter_button));
    }

    public ViewInteraction getAuthorizationText() {
        return onView(withText("Authorization"));
    }

    // Сделали метод статическим
    public static void checkErrorMessageDisplayed(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
