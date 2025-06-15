package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AppBarPage {

    // Иконка "бургер" навигационного меню
    public ViewInteraction getNavigationButton() {
        return onView(withId(R.id.main_menu_image_button));
    }

    // "News" в навигационном меню
    public ViewInteraction getNavigationNews() {
        return onView(allOf(withId(android.R.id.title), withText("News")));
    }

    // "About" в навигационном меню
    public ViewInteraction getNavigationAbout() {
        return onView(allOf(withId(android.R.id.title), withText("About")));
    }

    // "Main" в навигационном меню
    public ViewInteraction getNavigationMain() {
        return onView(allOf(withId(android.R.id.title), withText("Main")));
    }

    // Кнопка "бабочка" (Our Mission)
    public ViewInteraction getLoveIsAllBtn() {
        return onView(allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission")));
    }

    // Кнопка выхода (иконка "человечек")
    public ViewInteraction getExitButton() {
        return onView(withId(R.id.authorization_image_button));
    }

    // Текст "Log out"
    public ViewInteraction getLogOutText() {
        return onView(allOf(withId(android.R.id.title), withText("Log out")));
    }
}
