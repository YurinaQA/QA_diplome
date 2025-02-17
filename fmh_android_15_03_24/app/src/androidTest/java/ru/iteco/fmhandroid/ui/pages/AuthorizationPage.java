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

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;


public class AuthorizationPage extends DataGenerator {
    //Поле для ввода логина
    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction loginFieldAsTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));

    //Поле для ввода пароля
    public static ViewInteraction passwordField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction passwordFieldAsTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));

    //Кнопка авторизации
    public static ViewInteraction loginButton = onView(withId(R.id.enter_button));

    //Заголовок авторизации
    public static ViewInteraction AuthorizationText = onView(withText("Authorization"));
   // public static ViewInteraction AuthorizationText = onView(allOf(withText("Authorization"), withId(R.id.nav_host_fragment)));

    //Появление ошибок
    public static void errorMessageText(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }




}


