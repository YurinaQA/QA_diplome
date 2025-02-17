package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.VersionText;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.textCopyRight;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.textPrivacyPolicy;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.textPrivacyPolicyLink;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.textTermsOfUse;
import static ru.iteco.fmhandroid.ui.pages.AboutPage.textTermsOfUseLink;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class AboutStep extends DataGenerator {

    //Проверка отображения надписи "Version"
    public static void checkVersionTitle() {
        waitUntilElement(R.id.about_version_title_text_view);
        VersionText.check(matches(isDisplayed()));
        VersionText.check(matches(withText(versionText)));
    }

    //Проверка отображения надписи "Privacy Policy:"
    public static void checkPrivacyPolicy() {
        waitUntilElement(R.id.about_privacy_policy_label_text_view);
        textPrivacyPolicy.check(matches(isDisplayed()));
        textPrivacyPolicy.check(matches(withText(privacyPolicy)));
    }

    //Проверка отображения надписи "Terms of use:"
    public static void checkTermsOfUse() {
        waitUntilElement(R.id.about_terms_of_use_label_text_view);
        textTermsOfUse.check(matches(isDisplayed()));
        textTermsOfUse.check(matches(withText(termsOfUse)));
    }

    //Проверка отображения ссылки "Privacy Policy:"
    public static void checkPrivacyPolicyLinkText() {
        waitUntilElement(R.id.about_privacy_policy_value_text_view);
        textPrivacyPolicyLink.check(matches(isDisplayed()));
    }

    //Проверка отображения ссылки "Terms of use:"
    public static void checkTermsOfUseLinkText() {
        waitUntilElement(R.id.about_terms_of_use_value_text_view);
        textTermsOfUseLink.check(matches(isDisplayed()));
    }

    //Проверка отображения ссылки копирайта
    public static void checkCopyRight() {
        waitUntilElement(R.id.about_company_info_label_text_view);
        textCopyRight.check(matches(isDisplayed()));
    }



}

