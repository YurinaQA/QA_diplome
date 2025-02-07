package ru.iteco.fmhandroid.ui.pages;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import androidx.test.espresso.ViewInteraction;

public class AuthorizationPage extends DataGenerator {

    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction passwordField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction loginButton = onView(withId(R.id.enter_button));
    public static ViewInteraction titleTextElement = onView(withText(AutorizationTitle));
    public static ViewInteraction textView = onView(withId(R.id.container_list_news_include_on_fragment_main));
//    public static ViewInteraction logOut = onView(allOf(withId(android.R.id.title), withText("Log out")));
//    public static ViewInteraction exitBtn = onView(allOf(withId(R.id.authorization_image_button)));
}


