package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class AppBarPage extends DataGenerator {
    //Иконка "бургер" навигационного меню
    public static ViewInteraction navigationButton = onView(withId(R.id.main_menu_image_button));

    //"News" в навигационном меню
    public static ViewInteraction navigationNews = onView(allOf(withId(android.R.id.title),
            withText("News")));

    //"About" в навигационном меню
    public static ViewInteraction navigationAbout = onView(allOf(withId(android.R.id.title),
            withText("About")));

    //"Main" в навигационном меню
    public static ViewInteraction navigationMain = onView(allOf(withId(android.R.id.title),
            withText("Main")));

    //Кнопка "бабочка" для перехода на страницу "Love is all"
    public static ViewInteraction loveIsAllBtn = onView(
            allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission")));

    //Кнопка выхода, иконка "Человечек"
    public static ViewInteraction exitBtn = onView(withId(R.id.authorization_image_button));

    //Текст "Log out"
    public static ViewInteraction logOut = onView(allOf(withId(android.R.id.title), withText("Log out")));

}
