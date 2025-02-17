package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AppBarStep;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTest extends DataGenerator {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void loginAuth() {
        try {
            MainStep.checkNewsTitle();
        } catch (androidx.test.espresso.PerformException e) {
            AuthorizationStep.loginFieldInput(validLogin);
            AuthorizationStep.passwordFieldInput(validPassword);
            AuthorizationStep.clickLoginBtn();
        }
        MainStep.checkNewsTitle();
        AppBarStep.clickNavigationBtn();
        AppBarStep.clickNavigationAbout();
        AboutStep.checkVersionTitle();
    }

    @Test
    @Story("Проверка отображения PrivacyPolicy")
    public void shouldViewPrivacyPolicy() {
        AboutStep.checkPrivacyPolicy();
    }

    @Test
    @Story("Проверка отображения TermsOfUse")
    public void shouldViewTermsOfUse() {
        AboutStep.checkTermsOfUse();
    }

    @Test
    @Story("Проверка отображения ссылки PrivacyPolicy")
    public void shouldViewPrivacyPolicyLink() {
        AboutStep.checkPrivacyPolicyLinkText();
    }

    @Test
    @Story("Проверка отображения ссылки TermsOfUse")
    public void shouldViewTermsOfUseLink() {
        AboutStep.checkTermsOfUseLinkText();
    }

    @Test
    @Story("Проверка отображения копирайта")
    public void shouldViewCopyRight() {
        AboutStep.checkCopyRight();
    }


    @Test
    @Story("Проверка перехода по ссылке PrivacyPolicy")
    public void shouldGoToPrivacyPolicy() {
        Intents.init();
        AboutPage.textPrivacyPolicyLink.perform(click()); // Для срабатывания Intent
        intended(hasData("https://vhospice.org/#/privacy-policy/")); // Проверка Intent
        Intents.release(); // Для “очистки” записей Intents
    }

    @Test
    @Story("Проверка перехода по ссылке TermsOfUse")
    public void shouldGoToTermsOfUse() {
        Intents.init();
        AboutPage.textTermsOfUseLink.perform(click()); // Для срабатывания Intent
        intended(hasData("https://vhospice.org/#/terms-of-use")); // Проверка Intent
        Intents.release(); // Для “очистки” записей Intents
    }

}
