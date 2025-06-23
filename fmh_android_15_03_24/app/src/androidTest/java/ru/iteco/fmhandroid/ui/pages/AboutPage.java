package ru.iteco.fmhandroid.ui.pages;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    // Приватные поля для хранения взаимодействий с элементами
    private ViewInteraction versionText;
    private ViewInteraction textPrivacyPolicy;
    private ViewInteraction textTermsOfUse;
    private ViewInteraction textPrivacyPolicyLink;
    private ViewInteraction textTermsOfUseLink;
    private ViewInteraction textCopyRight;

    // Конструктор для инициализации элементов
    public AboutPage() {
        initElements();
    }

    private void initElements() {
        versionText = Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.about_version_title_text_view),
                        ViewMatchers.withText("Version:"),
                        ViewMatchers.withParent(
                                ViewMatchers.withParent(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class)
                                )
                        )
                )
        );

        textPrivacyPolicy = Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.about_privacy_policy_label_text_view),
                        ViewMatchers.withText("Privacy Policy:"),
                        ViewMatchers.withParent(
                                ViewMatchers.withParent(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class)
                                )
                        ),
                        ViewMatchers.isDisplayed()
                )
        );

        textTermsOfUse = Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.about_terms_of_use_label_text_view),
                        ViewMatchers.withText("Terms of use:"),
                        ViewMatchers.withParent(
                                ViewMatchers.withParent(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class)
                                )
                        ),
                        ViewMatchers.isDisplayed()
                )
        );

        textPrivacyPolicyLink = Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.about_privacy_policy_value_text_view),
                        ViewMatchers.withText("https://vhospice.org/#/privacy-policy/"),
                        ViewMatchers.withParent(
                                ViewMatchers.withParent(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class)
                                )
                        )
                )
        );

        textTermsOfUseLink = Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.about_terms_of_use_value_text_view),
                        ViewMatchers.withText("https://vhospice.org/#/terms-of-use"),
                        ViewMatchers.withParent(
                                ViewMatchers.withParent(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class)
                                )
                        )
                )
        );

        textCopyRight = Espresso.onView(
                ViewMatchers.withId(R.id.about_company_info_label_text_view)
        );
    }

    // Методы для работы с элементами страницы
    public ViewInteraction getVersionText() {
        return versionText;
    }

    public ViewInteraction getTextPrivacyPolicy() {
        return textPrivacyPolicy;
    }

    public ViewInteraction getTextTermsOfUse() {
        return textTermsOfUse;
    }

    public ViewInteraction getTextPrivacyPolicyLink() {
        return textPrivacyPolicyLink;
    }

    public ViewInteraction getTextTermsOfUseLink() {
        return textTermsOfUseLink;
    }

    public ViewInteraction getTextCopyRight() {
        return textCopyRight;
    }
}
