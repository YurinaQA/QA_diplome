package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.steps.AuthorizationStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest extends DataGenerator {

    private final AuthorizationStep authorizationStep = new AuthorizationStep();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    @Story("Авторизация с валидными данными")
    public void userShouldAuthorizeWithValidCredentials() {
        authorizationStep.checkAuthorizationPage();
        authorizationStep.loginFieldInput(validLogin);
        authorizationStep.passwordFieldInput(validPassword);
        authorizationStep.clickLoginBtn();
    }
}
