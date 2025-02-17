package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class AboutPage {


    //Отображения "About"
    public static ViewInteraction VersionText = onView(allOf(withId(R.id.about_version_title_text_view),
            withText("Version:"),
            withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class)))));

    //Отображение "Privacy Policy:"
    public static ViewInteraction textPrivacyPolicy = onView(
            allOf(withId(R.id.about_privacy_policy_label_text_view), withText("Privacy Policy:"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                    isDisplayed()));

    //Отображение "Terms of use:"
    public static ViewInteraction textTermsOfUse = onView(
            allOf(withId(R.id.about_terms_of_use_label_text_view), withText("Terms of use:"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                    isDisplayed()));

    //Отображение ссылки "Privacy Policy:"
    public static ViewInteraction textPrivacyPolicyLink = onView(
            allOf(withId(R.id.about_privacy_policy_value_text_view), withText("https://vhospice.org/#/privacy-policy/"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //Отображение ссылки "Terms of use:"
    public static ViewInteraction textTermsOfUseLink = onView(
            allOf(withId(R.id.about_terms_of_use_value_text_view), withText("https://vhospice.org/#/terms-of-use"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //Отбражение копирайта
    public static ViewInteraction textCopyRight = onView(
            withId(R.id.about_company_info_label_text_view));
}
